package ru.job4j.book;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Book.
 */
@Entity
@Table(name = "books")
public class Book {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the book.
     */
    private String name;

    /**
     *
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Author> authors = new ArrayList();

    /**
     * Factory method.
     * @param name name of the book.
     * @return new book.
     */
    public static Book of(String name) {
        Book book = new Book();
        book.setName(name);
        return book;
    }

    /**
     * Adds author to the book.
     * @param author new author.
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
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
     * Gets name of the book.
     * @return name of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the book.
     * @param name new name of the book.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets all authors.
     * @return list of the authors.
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets new list of the authors.
     * @param authors new list of the authors.
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
