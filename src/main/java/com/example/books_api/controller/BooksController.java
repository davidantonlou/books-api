package com.example.books_api.controller;

import com.example.books_api.entity.*;
import com.example.books_api.service.BooksService;
import com.example.books_api.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @PostMapping(path = "books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity createBook(@RequestBody String request) throws ParseException {
        Response response = new Response(HttpStatus.CREATED,
                Arrays.asList(booksService.createBook(request)));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getBook(@PathVariable("id") Long bookId) {
        Response response;
        try {
            response = new Response(HttpStatus.OK,
                    Arrays.asList(booksService.getBook(bookId)));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(ResponseStatusException e) {
            response = new Response(HttpStatus.NOT_FOUND, new ArrayList<>());
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "external-books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getExternalBooks(@RequestParam(value = "name") String bookName) {
        ResponseExternal response;
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", Constants.USER_AGENT);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<ExternalBook[]> externalBooks = restTemplate.exchange(
                    Constants.EXTERNAL_API.concat("?name=").concat(bookName),
                    HttpMethod.GET, entity, ExternalBook[].class);

            response = new ResponseExternal(HttpStatus.OK,
                    Arrays.asList(externalBooks.getBody()));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(ResponseStatusException e) {
            response = new ResponseExternal(HttpStatus.NOT_FOUND, new ArrayList<>());
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllBooks() {
        Response response = new Response(HttpStatus.OK,
                booksService.getAllBooks());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteBookDelete(@PathVariable("id") Long bookId) {
        return deleteBook(bookId);
    }

    @PostMapping(path = "books/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteBookPost(@PathVariable("id") Long bookId) {
        return deleteBook(bookId);
    }

    @PutMapping(path = "books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateBookPut(@RequestBody String request, @PathVariable("id") Long bookId) {
        return updateBook(bookId, request);
    }

    @PostMapping(path = "books/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateBookPost(@RequestBody String request, @PathVariable("id") Long bookId) {
        return updateBook(bookId, request);
    }

    private ResponseEntity updateBook(Long bookId, String request) {
        Response response;
        try {
            Book book = booksService.updateBook(bookId, request);
            response = new ResponseMessage(HttpStatus.OK,
                    Arrays.asList(book),
                    String.format(Constants.RESPONSE_MESSAGE, book.getName(), Constants.UPDATED));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(ResponseStatusException | ParseException e) {
            response = new Response(HttpStatus.NOT_FOUND, new ArrayList<>());
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity deleteBook(Long bookId) {
        Response response;
        try {
            Book book = booksService.deleteBook(bookId);
            response = new ResponseMessage(HttpStatus.NO_CONTENT,
                    new ArrayList<>(),
                    String.format(Constants.RESPONSE_MESSAGE, book.getName(), Constants.DELETED));
            return new ResponseEntity(response, HttpStatus.NO_CONTENT);
        } catch(ResponseStatusException e) {
            response = new Response(HttpStatus.NOT_FOUND, new ArrayList<>());
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }
}
