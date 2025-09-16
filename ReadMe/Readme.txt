===========================
 Auction System - README
===========================

1. Overview
-----------
This is a microservices-based Auction Platform where users can:
- Register and authenticate (via Keycloak)
- Add vehicles to the inventory
- Create and manage auctions
- Place bids on vehicles
- Handle billing for completed auctions
- Receive notifications for auction and bidding events

The system is built with Spring Boot, Docker Compose, MySQL, Kafka, and Eureka.
All external requests go through the API Gateway.

2. Technology Stack
-------------------
- Java 21, Spring Boot 3
- Spring Cloud Gateway (API Gateway)
- Spring Security (OAuth2 Resource Server)
- MySQL 8.4
- Kafka 3.7 + Zookeeper
- Eureka (Service Registry)
- Keycloak 25.0
- Docker & Docker Compose
- Lombok, MapStruct
- Maven

3. Architecture
---------------
Core services:
- API Gateway (single entry point, request routing, security enforcement)
- Identity Service (handles authentication, Keycloak integration)
- Inventory Service (manages vehicles)
- Auction Service (manages auctions lifecycle)
- Bidding Service (handles bids placed on auctions)
- Billing Service (handles billing and payment-related processes)
- Notification Service (sends notifications to users)
- Service Registry (Eureka service discovery)
- Kafka + Zookeeper (messaging backbone)
- MySQL (persistence, schema per service)

Supporting tool:
- Keycloak (authentication & authorization provider)

4. Setup Instructions
---------------------
a) Build all services:
   mvn clean package -DskipTests

b) Start with Docker Compose (production-like):
   docker-compose -f docker-compose.yml up -d

c) Start with Docker Compose (local test setup):
   docker-compose -f docker-compose-test.yml up -d

d) Stop all services:
   docker-compose down -v

5. API Gateway
--------------
- All external requests are routed through the API Gateway at http://localhost:8080
- Example route mappings:
  - /vehicles/** -> Inventory Service
  - /auctions/** -> Auction Service
  - /bids/** -> Bidding Service
  - /billing/** -> Billing Service
  - /auth/** -> Identity Service
- Internal services communicate via Eureka and Kafka.

6. Authentication
-----------------
- Keycloak is available at: http://localhost:9000
- Default admin credentials: admin / admin
- Realm: auction
- Example client: auction-client
- All services use JWT tokens from Keycloak for securing endpoints.
