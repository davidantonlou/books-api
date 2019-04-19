
# Spring API Rest

### Execute from command line:
To start Spring Boot application and run standalone, just execute the following command:
```bash
$> ./run.sh
```

### Execute from Docker container:
To start Spring Boot application inside a Docker container, just execute the following command:
```bash
$> ./run.sh docker
```


### Endpoints

* GET http://localhost:8080/api/v1/external-books?name={bookName}
* POST http://localhost:8080/api/v1/books
* GET http://localhost:8080/api/v1/books
* GET http://localhost:8080/api/v1/books/{id}
* DELETE http://localhost:8080/api/v1/books/{id}
* POST http://localhost:8080/api/v1/books/{id}/delete
* PUT http://localhost:8080/api/v1/books/{id}
* POST http://localhost:8080/api/v1/books/{id}/update