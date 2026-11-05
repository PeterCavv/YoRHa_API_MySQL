# CLAUDE.md

This file guides Claude Code when working in this repository. Follow these conventions strictly when creating, modifying, or reviewing code.

## Stack

- Java 21 + Spring Boot 3.x
- Spring Data JPA
- Architecture: Domain-Driven Design (DDD) organized by modules/bounded contexts
- Testing: JUnit 5

## Project structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ecommerce/
│   │           ├── ECommerceApplication.java
│   │           ├── shared/
│   │           │   ├── domain/
│   │           │   │   ├── AggregateRoot.java
│   │           │   │   ├── DomainEvent.java
│   │           │   │   ├── Entity.java
│   │           │   │   └── ValueObject.java
│   │           │   ├── infrastructure/
│   │           │   │   └── EventPublisher.java
│   │           │   └── application/
│   │           │       └── UseCase.java
│   │           ├── customer/
│   │           │   ├── domain/
│   │           │   │   ├── Customer.java
│   │           │   │   ├── CustomerId.java
│   │           │   │   ├── Email.java
│   │           │   │   └── CustomerRepository.java
│   │           │   ├── application/
│   │           │   │   ├── CustomerService.java
│   │           │   │   └── CreateCustomerUseCase.java
│   │           │   └── infrastructure/
│   │           │       ├── CustomerJpaRepository.java
│   │           │       ├── CustomerEntity.java
│   │           │       └── CustomerRepositoryImpl.java
│   │           ├── product/
│   │           │   ├── domain/
│   │           │   │   ├── Product.java
│   │           │   │   ├── ProductId.java
│   │           │   │   ├── Money.java
│   │           │   │   └── ProductRepository.java
│   │           │   ├── application/
│   │           │   │   └── ProductService.java
│   │           │   └── infrastructure/
│   │           │       ├── ProductJpaRepository.java
│   │           │       ├── ProductEntity.java
│   │           │       └── ProductRepositoryImpl.java
│   │           ├── order/
│   │           │   ├── domain/
│   │           │   │   ├── Order.java
│   │           │   │   ├── OrderId.java
│   │           │   │   ├── OrderItem.java
│   │           │   │   ├── OrderStatus.java
│   │           │   │   ├── OrderCreatedEvent.java
│   │           │   │   └── OrderRepository.java
│   │           │   ├── application/
│   │           │   │   ├── OrderService.java
│   │           │   │   ├── CreateOrderUseCase.java
│   │           │   │   └── dto/
│   │           │   │       ├── CreateOrderRequest.java
│   │           │   │       └── OrderResponse.java
│   │           │   └── infrastructure/
│   │           │       ├── OrderJpaRepository.java
│   │           │       ├── OrderEntity.java
│   │           │       ├── OrderItemEntity.java
│   │           │       └── OrderRepositoryImpl.java
│   │           └── web/
│   │               ├── CustomerController.java
│   │               ├── ProductController.java
│   │               └── OrderController.java
│   └── resources/
│       ├── application.yml
│       └── data.sql
└── test/
    └── java/
        └── com/
            └── ecommerce/
                └── order/
                    └── domain/
                        └── OrderTest.java
```

Each bounded context (`customer`, `product`, `order`, etc.) is self-contained and always follows the same three layers: `domain`, `application`, `infrastructure`. `shared/` contains only generic, reusable pieces across contexts (base classes, contracts), never business logic belonging to a specific context. `web/` is the HTTP entry layer and lives outside the modules, since it can orchestrate multiple contexts.

## Responsibilities per layer

### `domain/`
Holds the pure business model. No dependency on Spring, JPA, or external frameworks.

- **Aggregate roots** (`Customer`, `Product`, `Order`): extend `AggregateRoot`, encapsulate invariants, and expose behavior rather than anemic getters/setters.
- **Value Objects** (`Email`, `Money`, `CustomerId`, `ProductId`, `OrderId`): immutable, self-validating in the constructor.
- **Internal entities** (`OrderItem`): entities without a global identity, living inside an aggregate.
- **Domain Events** (`OrderCreatedEvent`): business facts that already happened, named in the past tense.
- **Repository interfaces** (`OrderRepository`): persistence contracts defined in the domain, with no implementation.
- **Status enums** (`OrderStatus`): including valid transitions where applicable.

Rule to decide if something belongs in `domain/`: **does this class express a business rule (what's valid, which invariant is protected)?** If yes, it goes here.

### `application/`
Orchestrates the domain to fulfill a use case. Contains no business rules, only coordination.

- **Use Cases** (`CreateOrderUseCase`, `CreateCustomerUseCase`): one class per use case, with a single public method such as `execute()`. Single responsibility.
- **Services** (`OrderService`, `CustomerService`): use only when several operations share dependencies/orchestration; if a use case is self-contained, prefer a dedicated Use Case instead of bloating a Service.
- **DTOs** (`CreateOrderRequest`, `OrderResponse`): input/output objects of the application layer, decoupled from the domain model and from JPA entities.

Rule to decide if something belongs in `application/`: **does this class execute a step of a process (call the repository, publish an event, map to a DTO)?** If yes, it goes here.

### `infrastructure/`
Technical implementations and adapters toward the outside world (DB, messaging, external APIs).

- **JPA Entities** (`CustomerEntity`, `ProductEntity`, `OrderEntity`, `OrderItemEntity`): persistence mapping, kept separate from the domain aggregate.
- **Spring Data Repositories** (`CustomerJpaRepository`, `ProductJpaRepository`, `OrderJpaRepository`): `JpaRepository<Entity, ID>` interfaces.
- **Repository implementations** (`CustomerRepositoryImpl`, `ProductRepositoryImpl`, `OrderRepositoryImpl`): implement the domain contract, acting as a **1:1 mapper** between `XxxEntity` (JPA) and the domain aggregate.
- **EventPublisher**: technical implementation for publishing domain events (Spring's `ApplicationEventPublisher` or another mechanism).

## Naming conventions

| Element | Convention | Example |
|---|---|---|
| Aggregate root | Business noun | `Order`, `Customer` |
| Value Object | Noun, immutable | `Email`, `Money`, `OrderId` |
| Use Case | Verb + noun + `UseCase` | `CreateOrderUseCase` |
| Service | Noun + `Service` | `OrderService` |
| Domain Event | Past-tense fact + `Event` | `OrderCreatedEvent` |
| JPA Entity | Aggregate + `Entity` | `OrderEntity` |
| Repository (domain) | Aggregate + `Repository` | `OrderRepository` |
| Repository (Spring Data) | Aggregate + `JpaRepository` | `OrderJpaRepository` |
| Repository implementation | Aggregate + `RepositoryImpl` | `OrderRepositoryImpl` |
| Controller | Aggregate + `Controller` | `OrderController` |

## General rules

- **Dependency direction**: `web` → `application` → `domain`. `infrastructure` implements `domain` contracts, never the other way around. `domain` depends on no other layer.
- **DTOs vs Domain**: controllers and use cases never expose or receive the domain aggregate directly; always go through DTOs (`CreateOrderRequest`, `OrderResponse`).
- **Explicit dependency injection**: always use constructor injection, avoid field-level `@Autowired`.
- **Domain tests**: business logic (`OrderTest`) is tested in isolation, without Spring context or a database.
- **New bounded contexts**: always replicate the same three-folder structure (`domain/application/infrastructure`), even if one starts out nearly empty.
- Before adding a new class, ask: **business rule, use-case orchestration, or technical infrastructure detail?** That question determines the layer.

## When generating new code

- Follow the folder structure exactly as documented above; do not create alternative folders (loose `services/`, `models/`, `dto/` outside their module, etc.).
- If a request doesn't make clear which bounded context it belongs to, ask before assuming.
- Do not change existing code unrelated to the requested task.

## DDD migration in progress

This project is being migrated to the structure above step by step. Read `docs/ddd-progress.md` at the start of each session: it holds the way of working (tutor mode, commit style), the design decisions taken and the current step. Update it at the end of each session.