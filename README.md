\# 🎟️ Seat Booking System — Concurrency \& Database Locking



A mini production-engineering project focused on one critical problem:



> \*\*What happens when multiple users try to book the same seat at the same time?\*\*



The goal is simple:



\*\*One seat must never be successfully booked by two users.\*\*



\---



\## 🎯 Problem Statement



In a seat-booking system, two users can send booking requests for the same seat at nearly the same time.



A naive implementation can allow both requests to:



1\. Read the seat as `AVAILABLE`

2\. Create a booking

3\. Mark the seat as `BOOKED`



This creates a \*\*race condition\*\* and can result in duplicate bookings.



This project recreates that production problem and solves it using \*\*database locking\*\*.



\---



\## 🛠️ Tech Stack



\- Java 17

\- Spring Boot

\- Spring Data JPA

\- Hibernate

\- MySQL

\- Maven

\- REST API

\- JMeter



\---



\## 🏗️ Core Domain



The project contains three main entities:



\### Show



Represents a movie/show.



\### Seat



Represents a seat belonging to a particular show.



Seat states:



```text

AVAILABLE

BOOKED

BLOCKED



Booking

Represents a booking made by a user.

Booking states:

INPROGRESS

COMPLETED

FAILED



🔌 API

Book a Seat

POST /booking



Request:

{

&#x20; "showId": 1,

&#x20; "seatId": 1,

&#x20; "userId": 101

}



Successful response:

{

&#x20; "message": "SEAT BOOKED SUCCESSFULLY !!",

&#x20; "seatId": 1,

&#x20; "showId": 1,

&#x20; "status": "COMPLETED",

&#x20; "userId": 101

}



✅ Validation \& Exception Handling

The API validates incoming requests before executing booking logic.

Examples:

Invalid request

HTTP 400 BAD REQUEST



Show not found

HTTP 404 NOT FOUND



Seat unavailable

HTTP 404 NOT FOUND



This prevents invalid requests from reaching the booking logic.

🔒 Concurrency Problem

The critical scenario:

User A ───────────────┐

&#x20;                     │

&#x20;                     ├── Book Seat A1

&#x20;                     │

User B ───────────────┘



Both requests target the same seat.

The system must guarantee:

Successful bookings = 1



Never:

Successful bookings = 2



🔐 Locking Approaches

1\. Optimistic Locking

The project initially explored optimistic locking using:

@Version

private Long version;



Hibernate uses the version during the update:

UPDATE seat

SET ...

WHERE id = ?

AND version = ?



Optimistic locking is useful when contention is relatively low.

2\. Pessimistic Locking

For this seat-booking scenario, the final implementation uses:

@Lock(LockModeType.PESSIMISTIC\_WRITE)



The seat is locked while the booking transaction is executing.

Conceptually:

Request A

&#x20;  ↓

Acquire database lock

&#x20;  ↓

Check seat

&#x20;  ↓

Create booking

&#x20;  ↓

Mark seat BOOKED

&#x20;  ↓

Commit

&#x20;  ↓

Release lock



Request B

&#x20;  ↓

Waits for the lock

&#x20;  ↓

Seat is now BOOKED

&#x20;  ↓

Booking rejected



🏆 Why Pessimistic Locking?

Seat booking can have high contention, especially for popular seats.

For this project, the priority is:

Prevent duplicate successful bookings even when multiple requests compete for the same seat.



Therefore, pessimistic locking was selected as the final strategy.

Optimistic locking was explored as a comparison and learning approach.

🧪 Testing

The project was tested for:

\- Valid booking

\- Invalid request

\- Non-existent show

\- Non-existent/unavailable seat

\- Already booked seat

\- Concurrent booking attempts

Concurrent Test

Using JMeter:

Concurrent requests: 2

Same show

Same seat



Result:

Request 1 → SUCCESS ✅

Request 2 → FAILED ❌



Database result:

Successful bookings for the same seat = 1



This confirms the core business invariant:

One seat cannot be successfully booked twice.



🚀 Running the Project

1\. Clone

git clone <repository-url>



2\. Create MySQL Database

CREATE DATABASE seat\_booking;



3\. Configure Database

Update application.properties with your MySQL credentials.

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/seat\_booking

spring.datasource.username=root

spring.datasource.password=YOUR\_PASSWORD



4\. Run the application

mvn spring-boot:run



Spring Boot initializes the database and loads the demo data from:

src/main/resources/data.sql



📂 Project Structure

src

└── main

&#x20;   ├── java

&#x20;   │   └── com.project3.seatBooking

&#x20;   │       ├── controller

&#x20;   │       ├── service

&#x20;   │       ├── repository

&#x20;   │       ├── entity

&#x20;   │       ├── DTO

&#x20;   │       └── exception

&#x20;   │

&#x20;   └── resources

&#x20;       ├── application.properties

&#x20;       └── data.sql



💡 Key Engineering Learnings

This project demonstrates:

\- Race conditions

\- Database concurrency

\- Transaction boundaries

\- JPA dirty checking

\- Optimistic locking

\- Pessimistic locking

\- Database-level concurrency protection

\- Validation

\- Exception handling

\- Concurrent API testing

\- Production-oriented API design

🎯 Core Invariant

The most important rule of this system:

ONE SEAT

&#x20;  ↓

MANY CONCURRENT REQUESTS

&#x20;  ↓

ONLY ONE SUCCESSFUL BOOKING



Correctness under concurrency is more important than simply making the API work for a single request.

