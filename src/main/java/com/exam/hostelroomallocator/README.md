# Hostel Room Allocator Backend

**Project name:** Hostel Room Allocator backend
**Student name:** Divine IZABAYO
**Student ID:** 27813
**Course:** Web Technology and Internet
**Instruction:** Adventist University of Central Africa(AUCA)
**Instructor:** Patrick DUSHIMIMANA

A Spring Boot REST API that helps housing staff assign dorm rooms and log repairs. A student submits a room allocation, a warden in charge of one hostel block handles it, and after check-in the warden logs maintenance requests.

## Technologies

- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven 

## Entities

`DutyArea`, `HostelBlock`, `Warden`, `Student`, `RoomAllocation`, `MaintenanceRequest` and the enum `AlllocationStatus` (`REQUESTED`, `APPROVED`, `CHECKED_IN`, `DECLINED`).

## How to run the API

### 1. Install the prerequisities
- JDK 21 or newer
- PostgreSQL running on `localhost:5432`
- Git

### 2. Clone the repository

```bash
git clone https://github.com/divine-025/hostel-room-allocator-backend.git

cd hostel-room-allocator-backend
```

### 3. Create the database

open `psql` or pgAdmin and run:

```sql
CREATE DATABASE hostel_db;
```

### 4. Configure the database connection

open `scr/main/resources/application.properties` and set your own postgreSQL password:

```properties 
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5432/hostel_db
spring.datasource.username=postgres
spring.datasourse.password= YOUR_POSTGRES_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
The tables are created automatically on the first start(`ddl-auto=update`).

### 5. Start the application

Linux / macOS:

```bash
./mvnw spring-boot:run
```
windows (PowerShell or Command Prompt):

```bash
mvnw.cmd spring-boot:run
```
one can also run `HostelRoomAllocationApplication` from their IDE. the API is ready when the console prints `Started HostelRoomAllocatorApplication`.
It listens on **http://localhost:8081**.

### 6. Test with postman

1. Open Postman and choose **Import**.
2. selecte `hostel-room-allocator-midterm.postman_collection.json` from the root of this repository.
3. Enter the seed data first (see below), then run the requests. Each requirement has one request named by its ID ( for example: A2, B1, C4 and what it will  be doing).
4. Replace the example ids in the URLs and bodies with the ids returned by your own `GET .../all` calls.

### seed data 

Enter the data through the API's own save endpoints, in this order, because later records need the ids of the earlier ones:

1. 4 duty areas: Maintenance, Security, Discipline, Welfare (nobody holds welfare)
2. 4 hostel blocks number 1, 2, 3, 4 (block 4 has no warden)
3. 5 student (two with last name `Ingabire`)
4. 3 wardens, each with a hostel block (one holds two duty areas, one holds one)
5. 10 room allocations in October and November 2026 using all four statuses 

Note: after requirement D1 is implemented, new room allocations always start as `REQUESTED` and dates in the past are rejected. move an allocation to another status with `PATCH /api/room-allocations/{id}/status`.

## Endpoints

Base URL: `http:..localhost:8081`

### part A: CRUD 

For `{path}` in `duty-area`, `hostel-blocks`, `wardens`, `students`, `room-allocations`:
| Method | URL | Purpose |
|---|---|---|
| POST | `/api/{path}/save` | save |
| GET | `/api/{path}/all` | list all|
| GET | `/api/{path}/{id}` | Get by id|
| PUT | `/api//{path}/update/{id}` | update |
| DELETE | `/api/{path}/delete/{id}` | Delete |

### Parts B and F

Each contains its ID and the method as well as the URL as is in the postman collection(hoste-room=allocator-midterm.postman_collection.json)

### Status rules (D2)
| Current status | Allowed next status |
|---|---|
| REQUESTED | APPROVED or DECLINED |
| APPROVED | CHECKED_IN or  DECLINED |
| CHECKED_IN, DECLINED | none |

## Project Strucure

```
src/main/java/com/exam/hostelroomallocator/
domain/ enities and the AllocationStatus enum 
repository/ Spring Data JPA repositories (derived and JPQL queries)
service/ business rules
controller/ REST endpoints
```
