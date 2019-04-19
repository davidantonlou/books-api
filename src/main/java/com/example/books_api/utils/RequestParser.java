package com.example.books_api.utils;

import com.example.books_api.entity.*;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;

public class RequestParser {

    public RequestParser() { }

    public Book processBook(String request) throws ParseException {
        Book book = new Book();
        Any jsonDeserialize = JsonIterator.deserialize(request);
        book.setName(jsonDeserialize.get("book").get("name").toString());
        book.setCountry(jsonDeserialize.get("book").get("country").toString());
        book.setIsbn(jsonDeserialize.get("book").get("isbn").toString());
        book.setPublisher(jsonDeserialize.get("book").get("publisher").toString());
        String bookReleaseDate = jsonDeserialize.get("book").get("release_date").toString().replaceAll("-","");
        book.setRelease_date(new SimpleDateFormat("yyyyMMdd").parse(bookReleaseDate));
        book.setNumber_of_pages(Integer.parseInt(jsonDeserialize.get("book").get("number_of_pages").toString()));
        HashSet<String> authors = new HashSet<>();
        Iterator<Any> authorIterator = jsonDeserialize.get("book").get("authors").iterator();
        while (authorIterator.hasNext()) {
            authors.add(authorIterator.next().toString());
        }
        book.setAuthors(authors);

        return book;
    }
}
