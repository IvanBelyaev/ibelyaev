package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Parser.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class Parser {
    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

    /**
     * Method parses xml-file.
     * The method counts the sum of the field attribute values of the entry tag.
     * @param source - source file.
     * @return the sum of the field attribute values of the entry tag.
     */
    public long parse(File source) {
        long result = -1;
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            ParserHandler parserHandler = new ParserHandler();
            xmlReader.setContentHandler(parserHandler);
            xmlReader.parse(source.getAbsolutePath());
            result = parserHandler.getSum();
        } catch (Exception e) {
            LOG.error("Error in the parse() method", e);
        }
        return result;
    }
}
