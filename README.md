# Products API

Products API is a REST API service to manage all marketplace needs in the project scope. It was developed during IT Bootcamp - Wave 5 as a part of the last group project.

This repository was created from the parent repository, to receive new functionality.

# Documentation

The user story about this endpoint its inside the folder ./docs/requirements

## Endpoints

See also the OpenAPI Specification (/swagger-ui.html while running).

- Base API Port: 8081
- Base Database Port: 5433

| Method   | URI       | Description    |
| :---------- | :--------- | :----------------------- |
| `GET` | `/api/v1/fresh-products/search` | Search for registered products |

## Requirements

- Java 11 and later

## Installation and Usage

Use the given Maven Wrapper and Docker to build a new service container

```bash
## 1. Clone project to local 
git clone https://github.com/Grupo9-ITBootcampMeli/pi-ml-products

## 2. Use Maven Wrapper to generate a new build  
./mvnw clean package

## 2.1. Optionally, build without tests 
./mvnw clean package -DskipTests

## 3. Start service via Docker 
docker-compose up

## 3.1. If you are recreating a container, build a new Docker image and delete the previous
docker-compose build --no-cache && docker-compose up -d && docker rmi -f $(docker images -f "dangling=true" -q)

```

## Authors
- [Guilherme Pereira](https://github.com/GuiSilva23)
