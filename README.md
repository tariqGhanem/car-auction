# Car Auction Platform — Spring Boot Microservices Skeleton

Services:
- service-registry (Eureka)
- api-gateway (Spring Cloud Gateway)
- identity-service (JWT auth placeholder)
- inventory-service (Vehicle CRUD placeholder)
- auction-service (Auction state machine placeholder)
- bidding-service (Bids + Proxy placeholder)
- billing-service (Payments/Invoices placeholder)
- common-libs/dto (shared DTOs)
- common-libs/security (placeholder for future shared security utils)

## Build
```bash
mvn -q -DskipTests package
```

## Run (order matters)
Terminal 1:
```bash
cd service-registry && mvn spring-boot:run
```

Terminal 2 (after registry is up on 8761):
```bash
cd api-gateway && mvn spring-boot:run
```

Other terminals (start any service you want):
```bash
cd identity-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd auction-service && mvn spring-boot:run
cd bidding-service && mvn spring-boot:run
cd billing-service && mvn spring-boot:run
```

Gateway routes (default ports):  
- Identity:   http://localhost:8080/identity/actuator/health  
- Inventory:  http://localhost:8080/inventory/actuator/health  
- Auction:    http://localhost:8080/auctions/actuator/health  
- Bidding:    http://localhost:8080/bids/actuator/health  
- Billing:    http://localhost:8080/billing/actuator/health  

> Note: This is a clean skeleton: each service exposes `/actuator/health` and one sample controller. 
> Replace in-memory storage with your DBs and implement real logic as you go.
