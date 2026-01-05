# CarShop System

A **Java Spring Boot** application for managing a car shop, including user authentication, vehicles, colors, brands, shopping carts, and orders. This system follows a layered architecture with **DTOs**, **models**, **repositories**, **services**, and **controllers**.

---

## Table of Contents

- [Features](#features)  
- [Architecture](#architecture)  
- [Technologies](#technologies)  
- [Project Structure](#project-structure)  
- [UML Diagram](#uml-diagram)  
- [Setup](#setup)  
- [Usage](#usage)  
- [Contributing](#contributing)  
- [License](#license)  

---

## Features

- User registration and login with role-based access (`ADMIN` and `USER`)  
- CRUD operations for **Vehicles**, **Colors**, **Brands**, and **Models**  
- Shopping cart management for clients  
- Order creation and payment processing  
- DTOs for data transfer between layers  
- Entity relationships with `Client`, `User`, `Vehicle`, `Order`, `Payment`, `ShoppingCart`, etc.

---

## Architecture

The project follows a **layered architecture**:

1. **Controllers**: Handle HTTP requests and responses.  
   - `AuthController` → User registration, login, and updates  
   - `ColorController` → Manage color information  

2. **Services**: Contain business logic.  
   - `AuthService` → Authentication and user management  
   - `ColorService` → Color management  
   - `ModelService` → Model management  

3. **Repositories**: Data access layer using Spring Data JPA.  
   - `ClientRepository`, `UserRepository`, `ColorRepository`, `VehicleRepository`, etc.  

4. **DTOs**: Data Transfer Objects for encapsulating data between layers.  
   - Examples: `ClientDTO`, `ColorDTO`, `ModelDTO`, `LoginResponseDTO`, `AuthDTO`  

5. **Models**: JPA entities representing the database structure.  
   - Examples: `User`, `Client`, `Vehicle`, `Order`, `Payment`, `ShoppingCartItem`  

6. **Enums**: Represent fixed sets of constants.  
   - `Role` → User roles  
   - `PaymentStatus` → Payment states  

---

## Technologies

- **Java 17+**  
- **Spring Boot**  
- **Spring Data JPA**  
- **Hibernate**  
- **MySQL** (or any JPA-supported relational database)  
- **Maven** (for dependency management)  

---

## Project Structure

