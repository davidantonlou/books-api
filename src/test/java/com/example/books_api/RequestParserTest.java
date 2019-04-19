package com.example.books_api;


import com.example.books_api.entity.Book;
import com.example.books_api.repository.BooksRepository;
import com.example.books_api.utils.Constants;
import com.example.books_api.utils.RequestParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RequestParserTest {

    private static final String REQUEST = "{\n" +
            "    \"book\": {\n" +
            "        \"name\": \"My First Book\",\n" +
            "        \"isbn\": \"123‐3213243567\",\n" +
            "        \"authors\": [\"John Doe\" ],\n" +
            "        \"number_of_pages\": 350,\n" +
            "        \"publisher\": \"Acme Books\",\n" +
            "        \"release_date\": \"2019-08-01\",\n" +
            "        \"country\": \"United States\"\n" +
            "    }\n" +
            "}";

    @Test
    public void testRequestParser() throws ParseException {
        RequestParser parser = new RequestParser();
        Book book = parser.processBook(REQUEST);
        assertNotNull(book);
        assertEquals("Check Book Name", book.getName(), "My First Book");
        assertEquals("Check Book ISBN", book.getIsbn(), "123‐3213243567");
        assertEquals("Check Book Pages", book.getNumber_of_pages(), 350);
        assertEquals("Check Book Publisher", book.getPublisher(), "Acme Books");
        assertEquals("Check Book Release Date", book.getRelease_date(), new SimpleDateFormat(Constants.DATE_FORMAT).parse("2019-08-01"));
        assertEquals("Check Book Country", book.getCountry(), "United States");
        assertEquals("Check Authors Size", book.getAuthors().size(), 1);
        assertTrue("Check Authors Content", book.getAuthors().contains("John Doe"));
    }

}