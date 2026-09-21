# Product Service

A microservice built with **Java** and **Spring Boot** for managing products and categories. The project implements relational persistence, database migrations, caching, and Docker containers for supporting infrastructure.

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot 3.x**
  * Spring Data JPA
  * Spring WebMVC
  * Spring Boot Actuator
  * Spring Cache
  * Spring Validation
* **PostgreSQL** — Relational Database
* **Redis** — In-memory Caching
* **Flyway** — Database Migration & Versioning
* **Lombok** — Boilerplate Code Reduction
* **Docker & Docker Compose** — Local Infrastructure Orchestration
* **Gradle** — Dependency Management and Build Tool

---

## 📋 Domain Model

The service manages the relationship between categories and products:
* **Category**: Can contain multiple products ($1:N$).
* **Product**: Belongs to a single category ($N:1$).

---

## 🏗️ Project Architecture

The source code follows a clean, layered architecture:

```text
com.example.product_service
├── config/          # Infrastructure configurations (Redis, Cache, etc.)
├── controller/      # REST API Controllers
├── domain/          # JPA Entities (Product, Category)
├── dto/             # Data Transfer Objects (Request / Response)
├── mapper/          # Mappers between Entities and DTOs
├── repository/      # Spring Data JPA Repositories
└── service/         # Business Logic Layer
