package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ParserHandler.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class ParserHandler  extends DefaultHandler {
    /** The amount. */
    private long sum = 0;

    /**
     * The method counts the sum of the field attribute values of the entry tag.
     * The method is called when the parser enters the tag.
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException - SAXExceptions.
     */
    public void startElement(String uri, String localName,
                              String qName, Attributes attributes) throws SAXException {
        if (localName.equals("entry")) {
            sum += Integer.parseInt(attributes.getValue("field"));
        }
    }

    /**
     * Get sum.
     * @return sum.
     */
    public long getSum() {
        return sum;
    }
}
