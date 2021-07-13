package ru.job4j.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author.
 */
@Entity
@Table(name = "authors")
public class Author {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Author's name. */
    private String name;

    /**
     * Factory method.
     * @param name author's name.
     * @return new author.
     */
    public static Author of(String name) {
        Author author = new Author();
        author.setName(name);
        return author;
    }

    /**
     * Gets id.
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets author's name.
     * @return author's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets author's name.
     * @param name new author's name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
