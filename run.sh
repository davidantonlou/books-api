#!/bin/bash

gradle build

if [[ "$1" == "docker" ]]; then
    docker rm books_api --force | true
    docker rmi books_api | true

    docker build -t books_api .
    docker run -d --name books_api -p 8080:8080 books_api
    docker logs -f books_api
else
    gradle bootRun
fi