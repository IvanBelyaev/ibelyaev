package ru.job4j.parser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * SqlRuParser.
 * A class runs through sql.ru looking for Java jobs.
 * Jobs are saved to the database.
 * @author Ivan Belyaev
 * @since 16.12.2019
 * @version 2.0
 */
public class SqlRuParser implements Job {
    /** Temporary job storage. */
    private final List<Vacancy> list = new LinkedList<>();
    /** Date parser. */
    private final DateParser dateParser = new DateParser();
    /** Jobs later than what date are considered new. */
    private LocalDateTime readUpDataTime;
    /** Last date in current search. */
    private LocalDateTime tempReadUpDataTime;
    /** The object that controls database actions. */
    private final DatabaseManager dbManager = new DatabaseManager();
    /** Application properties. */
    private Properties properties = new Properties();
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class);

    /**
     * Entry point.
     * Program launch examples:
     * SqlRuParser - program searches application properties in sqlRuParser.properties file.
     * SqlRuParser fileWithProperties - the program retrieves properties from fileWithProperties.
     * SqlRuParser fileWithProperties re-parse=off - to turn off automatic re-parsing.
     * @param args - command line arguments.
     * @throws SchedulerException - Quartz exceptions.
     */
    public static void main(String[] args) throws SchedulerException {
        SqlRuParser sqlRuParser = new SqlRuParser();
        boolean useAutoUpdate = true;
        String propertiesFileName = "sqlRuParser.properties";
        if (args.length > 0) {
            propertiesFileName = args[0];
            if (args.length == 2 && "re-parse=off".equals(args[1])) {
                useAutoUpdate = false;
            }
        }
        sqlRuParser.loadProperties(propertiesFileName);
        if (useAutoUpdate) {
            sqlRuParser.setAutoUpdate();
        }
        sqlRuParser.parseSqlRu();
    }

    /**
     * Method loads properties from file.
     * @param propertiesFileName - properties file.
     */
    private void loadProperties(String propertiesFileName) {
        try (InputStream in = SqlRuParser.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(in);
        } catch (IOException e) {
            LOG.error("Properties does not load.", e);
        }
    }

    /**
     * The method sets the interval for repeated parsing.
     */
    private void setAutoUpdate() {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("properties", properties);

            JobDetail job = JobBuilder.newJob(SqlRuParser.class)
                    .withIdentity("parser", "group1")
                    .usingJobData(jobDataMap)
                    .build();

            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule(this.properties.getProperty("cron.time")))
                    .build();

            sched.start();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error("Quartz error", e);
        }
    }

    /**
     * The method iterates through pages with vacancies and saves vacancies in the database.
     */
    private void parseSqlRu() {
        dbManager.setProperties(properties);
        dbManager.connect();
        readUpDataTime = dbManager.getReadUpDataTime();
        tempReadUpDataTime = readUpDataTime;
        boolean flag = true;
        int index = 1;
        while (flag) {
            try {
                Document document = Jsoup.connect("https://www.sql.ru/forum/job/" + index).get();
                Elements elements = document.select(".forumTable tr");
                flag = this.parsePage(elements);
                index++;
            } catch (IOException e) {
                LOG.error("Cannot download page https://www.sql.ru/forum/job/" + index, e);
            }
        }
        dbManager.updateDB(list, tempReadUpDataTime);
    }

    /**
     * The method receives elements of the page with work and processes them in search of java vacancies.
     * @param elements - sql.ru forum table rows.
     * @return true if new vacancies have not ended otherwise returns false.
     */
    private boolean parsePage(Elements elements) {
        boolean readMore = true;
        Iterator<Element> elementIterator = elements.listIterator(4);
        while (elementIterator.hasNext()) {
            Element element = elementIterator.next();
            LocalDateTime dateTime = dateParser.parseDate(element.select("td").last().text());
            if (dateTime.isAfter(readUpDataTime)) {
                Element titleElement = element.select(".postslisttopic a").first();
                String name = titleElement.text();
                if (isTopicAboutJava(name)) {
                    String link = titleElement.attr("href");
                    addJavaTopic(name, link);
                }
                if (dateTime.isAfter(tempReadUpDataTime)) {
                    tempReadUpDataTime = dateTime;
                }
            } else {
                readMore = false;
                break;
            }
        }
        return readMore;
    }

    /**
     * The method parses the date of publication of the vacancy,
     * its description, and if the vacancy is new, adds it.
     * @param name - job title.
     * @param link - link to a vacancy.
     */
    private void addJavaTopic(String name, String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Element startingPost = document.select("table.msgTable").first();
            String text = startingPost.select("td.msgBody").last().text();
            LocalDateTime dateTime =
                    dateParser.parseDate(
                            startingPost
                                    .select("td.msgFooter")
                                    .text()
                                    .split("\\[")[0]
                                    .replace("\u00A0", "")
                    );
            if (dateTime.isAfter(readUpDataTime)) {
                list.add(new Vacancy(name, text, link));
            }
        } catch (IOException e) {
            LOG.error("Cannot download page " + link, e);
        }
    }

    /**
     * The method will check if this vacancy is a java vacancy.
     * @param name - job title.
     * @return true if topic about Java otherwise returns false.
     */
    private boolean isTopicAboutJava(String name) {
        name = name.toLowerCase();
        return name.contains("java") && !name.contains("script");
    }

    /**
     * Method starts with automatic re-parsing.
     * @param jobExecutionContext - jobExecutionContext.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        this.properties = (Properties) jobDataMap.get("properties");
        this.parseSqlRu();
    }
}
