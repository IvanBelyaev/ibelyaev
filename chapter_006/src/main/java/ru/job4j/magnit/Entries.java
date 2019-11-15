package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Entries.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
@XmlRootElement
public class Entries {
    /** Entries. */
    private List<Entry> entry;

    /**
     * The constructor creates the object Entries.
     */
    public Entries() {

    }

    /**
     * The constructor creates the object Entries.
     * @param entries - entries.
     */
    public Entries(List<Entry> entries) {
       this.entry = entries;
    }

    /**
     * Get entries.
     * @return entries.
     */
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * Set entries.
     * @param entry - entries.
     */
    @XmlElement(name = "entry")
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
