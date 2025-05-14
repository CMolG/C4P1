
# Capi Shop
Simple shop demo using Quarkus + Liquibase + Docker.

In this example, I tried to follow Hexagonal Architecture principles proposed by Netflix in their tech blog.

It has Category, Product and Promotion entities. This is due to future necessity of upgrade. It can contain Inventory as well, but I added the minimum for serving a demo.

# Requirements
* JDK 17+
* Maven 
* Running Docker

# Step by step
Clone the repository:

```bash
git clone https://github.com/CMolG/C4P1.git
```

Compile:
```bash
mvn clean compile
```

Deploy dev version:
```bash
mvn quarkus:dev
```

Run tests:
```bash
mvn clean test
```
Integration tests spin up an ephemeral PostgreSQL using Testcontainers, so Docker must be running.

Endpoint REST by default: http://localhost:8080/products?page=1&size=10