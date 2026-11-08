# Hand2 – Full-Stack Second-Hand Marketplace Platform

A modern, high-performance second-hand marketplace application engineered with a **Java Spring Boot** RESTful backend and a **Vue 3** single-page application (SPA) frontend.

<p align="center">
  <img width="1897" height="907" alt="Screenshot 2026-11-08 152022" src="https://github.com/user-attachments/assets/965880cb-2949-4dcd-b896-2c69cd0b825e" />
  <img width="1897" height="905" alt="Screenshot 2026-11-08 152041" src="https://github.com/user-attachments/assets/55fe3606-fa6e-43d6-bc30-081587752d7a" />
 <img width="1897" height="907" alt="Screenshot 2026-11-08 152157" src="https://github.com/user-attachments/assets/6660fa3b-8448-4a0a-af2b-f45f66a50f6c" />

</p>
---

## 🚀 Key Features & Architecture

* **Decoupled Architecture:** Separation of concerns between the Java enterprise backend and the reactive Vue 3 client.
* **State Management & Routing:** State management handled via **Pinia** and client-side navigation structured using **Vue Router**.
* **RESTful API & Static Media Handling:** Custom endpoints for dynamic product listings, search queries, and local media/image storage delivery.
* **Authentication & Authorization:** Secure user onboarding and authentication flows powered by **Spring Security** and **JWT (JSON Web Tokens)**.

---

## 🛠️ Tech Stack

### Frontend
* **Framework:** Vue 3 (Composition API)
* **Build Tool:** Vite
* **State Management:** Pinia
* **Routing:** Vue Router
* **Styling & UI:** Responsive CSS/RTL layout support

### Backend
* **Language & Framework:** Java, Spring Boot 3
* **Security:** Spring Security & JWT Token Management
* **Database:** SQL Server / JPA Hibernate ORM
* **Build Tool:** Maven

---

## 🔑 Demo Accounts

For testing and reviewing role-based functionalities:

| User Email | Password |
| :--- | :--- |
| `dani@hand2.co.il` | `1234` |
| `michal@hand2.co.il` | `1234` |
| `yossi@hand2.co.il` | `1234` |

---

## 📡 Primary API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| **GET** | `/api/product/getAll` | Retrieve all active product listings | ❌ |
| **GET** | `/api/images/{filename}` | Fetch static product media | ❌ |
| **POST** | `/api/auth/register` | Register a new user account | ❌ |
| **POST** | `/api/auth/login` | Authenticate user & return JWT token | ❌ |
| **POST** | `/api/product/addDto` | Publish a new marketplace listing | 🔒 (JWT) |

---

## 🚦 Quick Start & Local Setup

### Prerequisites
* JDK 17 or higher
* Node.js v18+ & npm

### 1. Backend Setup
```bash
# Run Spring Boot Application (Port 8585)
./mvnw spring-boot:run
