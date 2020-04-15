package ru.job4j.magnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * ConvertXSQT.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class ConvertXSQT {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(ConvertXSQT.class);

    /**
     * Method converts xml with xsl.
     * @param source - xml file source
     * @param dest - output file
     * @param scheme - xsl file
     */
    public void convert(File source, File dest, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerException e) {
            LOG.error("Error in the convert() method", e);
        }
    }
}