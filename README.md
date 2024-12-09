# Library Management API

## Overview
The **Library Management API** is a RESTful service that provides functionality to manage borrowers and books in a library system. It allows users to perform actions such as registering borrowers and books, retrieving book details, and managing book borrowing and returns.

---

## Features
- Register a new borrower.
- Register a new book.
- Retrieve all books.
- Borrow a book for a borrower.
- Return a borrowed book.

---

## Technologies
- **Spring Boot 3.4.0**
- **Java 17**
- **Spring Data JPA**
- **H2 (In-memory database for development)**
- **Springdoc OpenAPI (Swagger UI for API documentation)**

---

## Requirements
- **Java 17** or later
- **Maven** (for building the project)
- **Spring Boot 3.4.0**

---

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/muhammad-Osman/Library_management.git
cd library-management-api
```

### Change username, password and database name in application.properties
```bash
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=****
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=postgresql
````

### How to run
```bash
mvn clean install
````

### Swagger URL
```bash 
https://editor.swagger.io/?_gl=1*1u4klay*_gcl_au*ODkzMTczNzg3LjE3MzE2NzcxOTk.
```