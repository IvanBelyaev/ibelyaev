package ru.job4j.crud;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    public static Vacancy of(String description) {
        Vacancy vacancy = new Vacancy();
        vacancy.description = description;
        return vacancy;
    }

    public Vacancy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
