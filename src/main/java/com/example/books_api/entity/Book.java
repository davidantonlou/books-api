package com.example.books_api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="books")
public class Book extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="isbn")
    private String isbn;

    @ElementCollection
    @CollectionTable(name = "authors", joinColumns = @JoinColumn(name = "isbn"))
    @Column(name="authors")
    private Set<String> authors;

    @Column(name="country")
    private String country;

    @Column(name="number_of_pages")
    private Integer number_of_pages;

    @Column(name="publisher")
    private String publisher;

    @Column(name="release_date")
    private Date release_date;

    public Book() {}

    public Book(Long id, String name, String isbn, Set<String> authors, String country,
                Integer number_of_pages, String publisher, Date release_date) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.country = country;
        this.number_of_pages = number_of_pages;
        this.publisher = publisher;
        this.release_date = release_date;
    }

    public Book(String name, String isbn, Set<String> authors, String country,
                Integer number_of_pages, String publisher, Date release_date) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.country = country;
        this.number_of_pages = number_of_pages;
        this.publisher = publisher;
        this.release_date = release_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(Integer number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
}
