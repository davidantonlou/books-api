package com.example.books_api.entity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseMessage extends Response {

    private String message;

    public ResponseMessage(HttpStatus status, List<Book> books, String message) {
        super(status, books);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
