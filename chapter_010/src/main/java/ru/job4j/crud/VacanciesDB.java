package ru.job4j.crud;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "databases")
public class VacanciesDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String dbName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public static VacanciesDB of(String dbName) {
        VacanciesDB vacanciesDB = new VacanciesDB();
        vacanciesDB.dbName = dbName;
        return vacanciesDB;
    }

    public VacanciesDB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public String toString() {
        return "VacanciesDB{" +
                "id=" + id +
                ", dbName='" + dbName + '\'' +
                ", vacancies=" + vacancies +
                '}';
    }
}
