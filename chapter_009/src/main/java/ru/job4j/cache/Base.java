package ru.job4j.cache;

import java.util.Objects;

/**
 * Base.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 07.12.2020
 */
public class Base {
    /** ID. */
    private final int id;
    /** Version.*/
    private int version;

    /**
     * Constructor.
     * @param id ID.
     * @param version version.
     */
    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    /**
     * Returns ID.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns version.
     * @return version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets new version.
     * @param version new version.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Checks objects for equality.
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Base base = (Base) obj;
        return id == base.id
                && version == base.version;
    }

    /**
     * Returns a hash code value for the object.
     * @return hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    /**
     * Returns a string representation of the object.
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + '}';
    }
}