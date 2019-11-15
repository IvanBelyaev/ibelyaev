package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ParserTest.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class ParserTest {
    /** Folder for storing temporary files. */
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir") + File.separator;
    /** XSL template. */
    private String xsl = "<?xml version=\"1.0\"?>\n"
            + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
            + "    <xsl:output omit-xml-declaration=\"yes\" indent=\"yes\"/>\n"
            + "    <xsl:strip-space elements=\"*\"/>\n"
            + "\n"
            + "    <xsl:template match=\"/\">\n"
            + "            <entries>\n"
            + "               <xsl:for-each select=\"entries/entry\">\n"
            + "                    <entry>\n"
            + "                        <xsl:attribute name=\"field\">\n"
            + "                            <xsl:value-of select=\"field\"/>\n"
            + "                        </xsl:attribute>\n"
            + "                    </entry>\n"
            + "                </xsl:for-each>\n"
            + "            </entries>\n"
            + "    </xsl:template>\n"
            + "</xsl:stylesheet>";

    /**
     * Test to test the program.
     * @throws FileNotFoundException - exceptions.
     */
    @Test
    public void checkParser() throws FileNotFoundException {
        File source = new File(TMP_DIR  + "source.xml");
        File dest = new File(TMP_DIR  + "dest.xml");
        File scheme = new File(TMP_DIR  + "scheme.xsl");
        try (PrintWriter printWriter = new PrintWriter(scheme)) {
            printWriter.write(xsl);
            printWriter.flush();
        }
        StoreSQL storeSQL = new StoreSQL(new Config());
        int size = 10;
        storeSQL.generate(size);
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.load());
        new ConvertXSQT().convert(source, dest, scheme);
        long parserReturns = new Parser().parse(dest);

        assertThat(parserReturns, is((long) size * (size + 1) / 2));
    }
}
