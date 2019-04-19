package com.example.books_api.service;

import com.example.books_api.entity.Book;
import com.example.books_api.repository.BooksRepository;
import com.example.books_api.utils.Constants;
import com.example.books_api.utils.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private RequestParser requestParser;

    @Autowired
    private BooksRepository booksRepository;

    @PostConstruct
    public void init() {
        this.requestParser = new RequestParser();
    }

    public Book createBook(String request) throws ResponseStatusException, ParseException {
        Book book = this.requestParser.processBook(request);
        return booksRepository.save(book);
    }

    public Book getBook(Long bookId) throws ResponseStatusException {
        Optional<Book> book = booksRepository.findById(bookId);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.BOOK_NOT_EXIST, new Exception());
        }
    }

    public List<Book> getBookByName(String bookName) {
        return booksRepository.findByName(bookName);
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book deleteBook(Long bookId) throws ResponseStatusException {
        Book book = this.getBook(bookId);
        if (book != null) {
            booksRepository.deleteById(bookId);
            return book;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.BOOK_NOT_EXIST, new Exception());
        }
    }

    public Book updateBook(Long bookId, String request) throws ParseException {
        Book book = this.requestParser.processBook(request);
        if (this.getBook(bookId) != null) {
            booksRepository.save(book);
            return book;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.BOOK_NOT_EXIST, new Exception());
        }
    }
}