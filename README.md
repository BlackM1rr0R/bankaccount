# 📚 Book Rental API

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-database-blue?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-build-red?logo=apachemaven)
![Postman](https://img.shields.io/badge/Postman-testing-orange?logo=postman)

Welcome to **Book Rental API**!  
A simple and clean Spring Boot project to manage book rentals. 🚀

---

## 🌟 Features

### 📖 Book Operations
- Add a book  
- Update book information  
- Delete a book  
- List all available books  

### 🏷️ Rental Operations
- Rent a book  
- Return a book  
- View all rentals of a user  

---

## 🔗 REST API Endpoints

### **Books**

| Method | Endpoint      | Parameters | Description                |
|--------|---------------|------------|----------------------------|
| GET    | `/book`       | -          | List all available books   |
| POST   | `/book`       | JSON Body  | Add a new book             |
| PUT    | `/book/{id}`  | JSON Body  | Update an existing book    |
| DELETE | `/book/{id}`  | -          | Delete a book by ID        |

### **Rentals**

| Method | Endpoint               | Parameters           | Description                        |
|--------|------------------------|--------------------|------------------------------------|
| POST   | `/rental`              | userId, bookId      | Rent a book                        |
| PUT    | `/rental/{id}/return`  | -                  | Return a rented book               |
| GET    | `/rental/user/{userId}`| -                  | Get all rentals of a user          |

---
