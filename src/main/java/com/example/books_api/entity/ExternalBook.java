package com.example.books_api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class ExternalBook extends AbstractEntity {

    private String name;

    private String isbn;

    private Set<String> authors;

    private String country;

    private Integer numberOfPages;

    private String publisher;

    private Date released;

    public ExternalBook() {}

    public ExternalBook(String name, String isbn, Set<String> authors, String country,
                        Integer numberOfPages, String publisher, Date released) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.country = country;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.released = released;
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

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }
}
