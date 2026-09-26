# Grand Prix Predictor (based on MotoGP championship)

[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.3+-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)
[![AWS](https://custom-icon-badges.demolab.com/badge/AWS-%23FF9900.svg?logo=aws&logoColor=white)](https://aws.amazon.com/)

> A production-ready enterprise REST API showcasing **Domain-Driven Design (DDD)** and **Hexagonal Architecture (Ports & Adapters)** built with modern **Java 21** and **Spring Boot 3**.

---

## Overview

**GrandPrix Predictor** is a backend service developed as a demonstrator of clean architecture, domain modeling, and modern Java best practices. It models the core operations of a motorsport championship calendar and handles user prediction logic with automated scoring based on actual race results.

### Key Engineering Highlights
- **Pure Domain Core:** Business rules and invariants live entirely within the `domain` layer with **zero dependencies** on Spring, JPA/Hibernate, or third-party frameworks.
- **Modern Java 21 LTS:** Implements Java Records for immutable Value Objects and DTOs, Pattern Matching, Sealed Interfaces, and Virtual Threads for concurrent I/O.
- **Containerized & Cloud Ready:** Complete local orchestration with **Docker Compose** and incremental automated deployment to **AWS** (Ubuntu on Lightsail).

---

## Architecture & Project Structure

The project strictly follows the **Ports and Adapters (Hexagonal Architecture)** pattern organized around domain concepts:

```text
src/main/java/com/motogp/predictor
│
├── domain/                               # 🟢 Pure Business Logic (Framework-independent)
│   ├── model/                            # Aggregates, Entities, Value Objects
│   │   ├── championship/                 # Championship, ChampionshipEvent, Circuit
│   │   ├── rider/                        # Rider, Team, RiderNumber (Record)
│   │   ├── prediction/                   # Prediction (Aggregate Root), PredictionScore
│   │   └── shared/                       # Exceptions
│   └── repository/                       # Outbound Ports (Driven Domain Interfaces)
│       ├── ChampionshipRepository.java
│       ├── RiderRepository.java
│       └── PredictionRepository.java
│
├── application/                          # 🟡 Orchestration & Use Cases
│   ├── port/
│   │   ├── in/                           # Driving / Inbound Ports
│   │   │   ├── SubmitPredictionUseCase.java
│   │   │   ├── CreateRiderUseCase.java
│   │   └── out/                          # Driven Ports (External events / notifications)
│   │       └── ChampionshipRepositoryPort.java
│   └── service/                          # Application Services (Use Case implementations)
│       ├── CalculateUserPointsService.java
│       └── ChampionshipApplicationService.java
│
└── infrastructure/                       # 🔴 Adapters & Framework Configuration
    ├── adapter/
       ├── in/
       │   └── rest/                     # Inbound HTTP Adapters (Spring MVC Controllers)
       │       ├── controller/           # PredictionController, RiderController, EventController
       │       ├── dto/                  # Request / Response DTOs (Java Records)
       │       └── mapper/               # DTO <-> Application Command Mappers
       └── out/
           └── persistence/              # Outbound DB Adapters (Spring Data JPA)
               ├── entity/               # JPA Entities (@Entity, @Table)
               │   ├── PredictionJpaEntity.java
               │   ├── RiderJpaEntity.java
               │   └── EventJpaEntity.java
               ├── repository/           # Spring Data JPA interfaces
               ├── mapper/               # JPA Entity <-> Domain Model Mappers
               └── PredictionPersistenceAdapter.java
```

---

## Tech Stack & Tooling

| Area | Technology | Purpose |
| :--- | :--- | :--- |
| **Language** | Java 21 LTS | Pattern matching, records, virtual threads, sealed types |
| **Framework** | Spring Boot 3.x | DI container, REST controllers, transaction management |
| **Data & Persistence** | PostgreSQL 17, Spring Data JPA | Relational data persistence, indexed queries |
| **DevOps & Cloud** | Docker, Docker Compose, AWS Lightsail | Local environment orchestration & cloud deployment |

---

## ☁️ Deployment & Cloud Infrastructure

- **Containerization:** The application is packaged using multi-stage Docker builds to produce an optimized, lightweight runtime image.
- **AWS Infrastructure:** Hosted on an **AWS Lightsail** Ubuntu instance with a static public IP.
- **Process & Network:** The service runs behind a reverse proxy handling incoming traffic, container networking, and automatic restart policies via Docker Compose.

---

## Architectural Decisions (ADRs)

1. **Strict Separation of Domain vs. Persistence Models:**
   - *Decision:* `Prediction` (Domain Model) and `PredictionJpaEntity` (Database Model) are separate classes with bidirectional mappers.
   - *Rationale:* Protects core business rules from database schema modifications, ORM proxy behavior, and lazy-loading issues.
2. **Java 21 Records for Value Objects & DTOs:**
   - *Decision:* Strongly typed domain identifiers (`RiderId`, `ChampionshipId`) and API DTOs are defined as immutable Java Records.
   - *Rationale:* Eliminates primitive obsession and guarantees immutability across layer boundaries.
3. **Repository Interfaces as Domain Ports:**
   - *Decision:* Repository contracts are owned by the `domain` layer and implemented by JPA adapters in `infrastructure`.
   - *Rationale:* Follows the Dependency Inversion Principle (DIP), allowing persistence implementations to be swapped or tested without modifying domain code.

---

## Status & Roadmap (Pendientes)

The project is currently in the active development phase. The following features and integrations are planned or currently being implemented:

- [ ] **Autenticación y Autorización:** Implementación de seguridad con Spring Security y tokens JWT para control de acceso y gestión de usuarios. *(Working on it)*
- [ ] **Suite de Tests Unitarios:** Cobertura de lógica de dominio y servicios de aplicación mediante JUnit 5 y Mockito.
- [ ] **Caché con Redis:** Optimización de rendimiento para consultas frecuentes de calendarios y clasificaciones.
- [ ] **Tests de Arquitectura (ArchUnit):** Automatización de reglas de validación para garantizar el desacoplamiento de capas y prevenir fugas de frameworks en el dominio.

---

## Author

- **Alejandro Hortelano** - *Senior Backend Software Engineer*
- LinkedIn:(https://www.linkedin.com/in/alejandro-hortelano-de-la-morena-8333b8146/)
