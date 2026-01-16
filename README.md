# inventory-service

INVENTORY SERVICE
Overview

Manages product inventory with multiple batches and expiry dates.
Consumes stock based on earliest expiry first (FEFO).

Tech Stack
--------------
	Java 17
	Spring Boot
	Gradle
	H2 (in-memory DB)
	Liquibase
	
Build & Run
--------------
cd inventory-service
./gradlew clean build
./gradlew bootRun

Service starts on:

http://localhost:8081

DB Console
http://localhost:8081/h2-console

jdbc:h2:mem:inventorydb

API:
Get Inventory by Product
curl http://localhost:8081/inventory/1005

response

{
  "productId":1005,
  "productName":"Smartwatch",
  "batches":[...]
}

Reserve Stock

curl -X POST http://localhost:8081/inventory/update \
-H "Content-Type: application/json" \
-d '{
  "productId":1005,
  "quantity":3
}'

{
  "productId":1005,
  "productName":"Smartwatch",
  "quantity":3,
  "status":"RESERVED",
  "usedBatchIds":[5,7]
}
