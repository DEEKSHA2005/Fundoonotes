# Fundoo Notes Application (UC1 – UC16)

---

## Overview

Fundoo Notes is a full-stack backend application built using **Spring Boot**, implementing features like **User Authentication, Notes Management, Reminder Scheduler, Security (JWT), and Microservices Architecture**.

The project evolves step-by-step from a basic CRUD application to a **production-ready microservices system**.

---

## Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** MySQL
* **Security:** Spring Security + JWT
* **ORM:** Spring Data JPA
* **Messaging:** RabbitMQ
* **Scheduler:** Spring Scheduler
* **Build Tool:** Maven

---

## Project Structure

```plaintext
com.fundoo.notes
│
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── security
├── scheduler
├── exception
└── util
```

---

# Use Case Descriptions

---

## 🔹 UC1 — Create Basic Spring Boot Project

* Setup project using Spring Initializr
* Configure dependencies
* Run application successfully

---

## 🔹 UC2 — Create Note Entity

* Create Note class
* Add fields: id, title, content
* Map using JPA annotations

---

## 🔹 UC3 — CRUD Operations for Notes

* Create Note
* Get all notes
* Update note
* Delete note

---

## 🔹 UC4 — Add Database (MySQL)

* Configure MySQL connection
* Create tables automatically
* Persist notes data

---

## 🔹 UC5 — Add User Entity

* Create User class
* Fields: id, name, email, password

---

## 🔹 UC6 — User Registration

* Register new user
* Store encrypted password

---

## 🔹 UC7 — Login API

* Authenticate user
* Validate email & password

---

## 🔹 UC8 — Password Encryption

* Use BCryptPasswordEncoder
* Store hashed passwords

---

## 🔹 UC9 — Link Notes with User

* Add userId in Note
* Fetch notes by user

---

## 🔹 UC10 — Add Flags to Notes

* isPinned
* isArchived
* isTrashed

---

## 🔹 UC11 — JWT Authentication

* Generate JWT token on login
* Validate token for requests
* Protect APIs

---

## 🔹 UC12 — Secure APIs

* Use Spring Security
* Add JWT Filter
* Restrict access to endpoints

---

## 🔹 UC13 — Add Reminder Feature

* Add reminderTime field
* Schedule reminders

---

## 🔹 UC14 — Scheduler Implementation

* Run job every 1 minute
* Fetch due reminders
* Print notification
* Mark reminder as sent

---

## 🔹 UC15 — Batch Processing (Spring Batch)

* Configure batch job
* Process large data
* Store job metadata in DB
* Use batch tables

---

## 🔹 UC16 — Microservices Architecture

* Split application into services:

    * User Service
    * Notes Service
    * Notification Service
* Use RabbitMQ for communication
* Each service runs independently

---

# System Flow

```plaintext
User → Register/Login → Get JWT
     → Create Note
     → Reminder Time Set
     → Scheduler Runs
     → RabbitMQ Message
     → Notification Service
     → Reminder Output
```

---

# Security Flow

```plaintext
Login → JWT Token Generated
     → Token sent in header
     → JWT Filter validates request
     → Access granted
```

---

# API Endpoints

---

## 🔹 User APIs

### Register

```http
POST /api/users/register
```

### Login

```http
POST /api/users/login
```

---

## 🔹 Notes APIs

### Create Note

```http
POST /api/notes
```

Headers:

```plaintext
Authorization: Bearer <JWT_TOKEN>
```

Body:

```json
{
  "title": "Test",
  "content": "Hello",
  "reminderTime": "2026-05-02T14:36:00"
}
```

---

### Get Notes

```http
GET /api/notes
```

---

### Update Note

```http
PUT /api/notes/{id}
```

---

### Delete Note

```http
DELETE /api/notes/{id}
```

---

# Features Implemented

✔ CRUD operations

✔ User authentication

✔ JWT security

✔ Reminder scheduler

✔ Batch processing

✔ RabbitMQ messaging

✔ Microservices architecture

