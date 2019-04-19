package com.example.books_api;


import com.example.books_api.entity.Book;
import com.example.books_api.repository.BooksRepository;
import com.example.books_api.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BooksRepositoryTest {

    @Autowired
    BooksRepository booksRepository;

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @Test
    public void testInsertBook() throws ParseException {
        Set<String> authors = new HashSet<>();
        authors.add("New Author");
        Book savedBook = booksRepository.save(new Book("New Book", generateISBN(0), authors,
                "New Country", 200, "New Publisher",
                new SimpleDateFormat(Constants.DATE_FORMAT).parse("2019-01-01")));

        Optional<Book> findedBook = booksRepository.findById(savedBook.getId());
        assertNotNull(findedBook.get());
        assertEquals("Assert Book Name", findedBook.get().getName(), savedBook.getName());
    }

    @Test
    public void testGetBookById() {
        String bookName = "Book 1";
        List<Book> findedBooks = booksRepository.findByName(bookName);
        Optional<Book> findedBook = booksRepository.findById(findedBooks.get(0).getId());
        assertTrue("Check finded Book", findedBook.isPresent());
        assertNotNull(findedBook.get());
        assertEquals("Assert Book Name", findedBook.get().getName(), bookName);
    }

    @Test
    public void testGetBookAll() {
        List<Book> findedBooks = booksRepository.findAll();
        assertNotNull(findedBooks);
        assertEquals("Check Books List Size", findedBooks.size(), 100);
    }

    @Test
    public void testGetBookByName() {
        String bookName = "Book 1";
        List<Book> findedBooks = booksRepository.findByName(bookName);
        assertTrue("Check finded Books size", (findedBooks.size() > 0));
        assertNotNull(findedBooks.get(0));
        assertEquals("Assert Book Name", findedBooks.get(0).getName(), bookName);
    }

    @Test
    public void testDeleteBook() {
        String bookName = "Book 2";
        List<Book> findedBooks = booksRepository.findByName(bookName);
        booksRepository.deleteById(findedBooks.get(0).getId());

        Optional<Book> findedBook = booksRepository.findById(findedBooks.get(0).getId());
        assertFalse("Check finded Book", findedBook.isPresent());
    }

    @Test
    public void testUpdateBook() throws ParseException {
        Long bookId = new Long(1);
        Set<String> authors = new HashSet<>();
        authors.add("New Author");
        booksRepository.save(new Book(bookId, "New Book", generateISBN(0), authors,
                "New Country", 200, "New Publisher",
                new SimpleDateFormat(Constants.DATE_FORMAT).parse("2019-01-01")));

        Optional<Book> findedBook = booksRepository.findById(bookId);
        assertNotNull(findedBook.get());
        assertEquals("Assert Book Name", findedBook.get().getName(), "New Book");
    }


    public void initData() throws ParseException {
        Set<String> authors;
        for (int i=1; i<=100; i++) {
            authors = new HashSet<>();
            authors.add("Author ".concat(String.valueOf(i)));
            booksRepository.save(new Book("Book ".concat(String.valueOf(i)),
                    generateISBN(i), authors, "Country".concat(String.valueOf(i)),
                    (int)(Math.random()*100), "Publisher ".concat(String.valueOf(i)),
                    new SimpleDateFormat(Constants.DATE_FORMAT).parse("2019-01-01")));
        }


    }

    public String generateISBN(int i){
        return String.valueOf(i).concat(String.valueOf(i)).concat(String.valueOf(i));
    }
}