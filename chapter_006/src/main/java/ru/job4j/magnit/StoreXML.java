package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * StoreXML.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class StoreXML {
    /** XML file to save data. */
    private  File target;
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(StoreXML.class);

    /**
     * The constructor creates the object Config.
     * @param target - xml-file to save data.
     */
    StoreXML(File target) {
        this.target = target;
    }

    /**
     * The method saves the data in an xml file.
     * @param list - data to save.
     */
    public void save(List<Entry> list) {
        Entries entries = new Entries(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, target);
        } catch (JAXBException e) {
            LOG.error("Error in the save() method", e);
        }
    }
}
