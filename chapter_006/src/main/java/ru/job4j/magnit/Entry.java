package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entry.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
@XmlRootElement
public class Entry {
    /** Field. */
    private int field;

    /**
     * The constructor creates the object Entry.
     */
    public Entry() {

    }

    /**
     * The constructor creates the object Entry.
     * @param field - field.
     */
    public Entry(int field) {
        this.field = field;
    }

    /**
     * Get field.
     * @return field.
     */
    public int getField() {
        return field;
    }

    /**
     * Set field.
     * @param field - field.
     */
    public void setField(int field) {
        this.field = field;
    }
}
