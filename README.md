# E-commerce Microservices with Spring Cloud

This project is an **E-commerce application** implemented using **Spring Boot** and **Spring Cloud** microservices architecture. It demonstrates service discovery, API gateway routing, and modular microservices.

---

## Project Structure

The repository contains the following services:

1. **API Gateway Service** (`api-gateway-service`)  
   - Routes incoming requests to appropriate microservices.
   - Implements filters and security if needed.

2. **Discovery Service** (`discovery-service`)  
   - Uses **Eureka Server** for service registration and discovery.

3. **Inventory Service** (`inventory-service`)  
   - Manages product stock and inventory details.

4. **Notification Service** (`notification-service`)  
   - Handles notifications such as order confirmations and updates.

5. **Order Service** (`order-service`)  
   - Manages customer orders: creation, updates, and tracking.

6. **Product Service** (`product-serivce`)  
   - Manages product information, pricing, and availability.

---

## Technologies Used

- Java 11+
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Maven
- Docker & Docker Compose
- REST API

---

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker (optional, for containerized deployment)

### Clone Repository

```bash
git clone https://github.com/Hamzaakk/ecom-microservice-spting-cloud.git
cd ecom-microservice-spting-cloud
