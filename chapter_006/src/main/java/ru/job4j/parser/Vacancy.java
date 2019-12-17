package ru.job4j.parser;

/**
 * Vacancy.
 * Class for vacancy from sql.ru.
 * @author Ivan Belyaev
 * @since 15.12.2019
 * @version 2.0
 */
public class Vacancy {
    /** Job title. */
    private String name;
    /** Vacancy description. */
    private String text;
    /** Link to a vacancy. */
    private String link;

    /**
     * The constructor creates Vacancy.
     * @param name - job title.
     * @param text - vacancy description.
     * @param link - link to a vacancy.
     */
    public Vacancy(String name, String text, String link) {
        this.name = name;
        this.text = text;
        this.link = link;
    }

    /**
     * Method returns job title.
     * @return job title.
     */
    public String getName() {
        return name;
    }

    /**
     * Method returns vacancy description.
     * @return vacancy description.
     */
    public String getText() {
        return text;
    }

    /**
     * Method returns link to a vacancy.
     * @return link to a vacancy.
     */
    public String getLink() {
        return link;
    }
}
