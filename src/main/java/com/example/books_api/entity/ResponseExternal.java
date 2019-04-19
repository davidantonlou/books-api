package com.example.books_api.entity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseExternal extends AbstractEntity {

    private int status_code;

    private String status;

    private List<ExternalBook> data;

    public ResponseExternal() {}

    public ResponseExternal(HttpStatus status, List<ExternalBook> books) {
        this.status_code = status.value();
        this.status = status.getReasonPhrase().toLowerCase();
        this.data = books;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ExternalBook> getData() {
        return data;
    }

    public void setData(List<ExternalBook> data) {
        this.data = data;
    }
}
