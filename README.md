# 🌱 SpringVerse

<div align="center">

### Learn Spring Boot by Building Real-World Projects

A modular Spring Boot application containing multiple CRUD projects designed to help developers learn backend development from beginner to intermediate level.

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![License](https://img.shields.io/badge/License-MIT-green)

</div>

---

## 📖 About

**SpringVerse** is a collection of real-world Spring Boot CRUD projects organized into a **single modular application**.

Instead of creating separate repositories for each project, every module lives inside one clean Spring Boot project, making it easier to learn project organization, code reuse, and scalable backend development.

Whether you're a student, beginner, or preparing for backend interviews, SpringVerse provides hands-on experience with REST APIs, Spring Data JPA, Hibernate, Validation, Pagination, and more.

---

# 📦 Modules

| Project | Description |
|----------|-------------|
| 🎓 Student Management | Complete CRUD Operations |
| 👨‍💼 Employee Management | Employee CRUD & Search |
| 📚 Library Management | Book Management System |
| ✅ Todo App | Task Management |
| 📦 Product Inventory | Inventory & Stock Management |
| 📞 Contact Book | Contact Directory |
| 📝 Blog API | Blog & Post Management |
| 🎬 Movie Collection | Movie Catalog |

---

# 🏗 Project Structure

```text
src
└── main
    └── java
        └── com.aman.springverse
            │
            ├── config
            ├── exception
            ├── utils
            │
            ├── modules
            │   ├── blogs
            │   ├── contacts
            │   ├── employees
            │   ├── library
            │   ├── movies
            │   ├── products
            │   ├── students
            │   └── todos
            │
            └── SpringVerseApplication.java
```

Each module follows a clean layered architecture:

```text
module
│
├── controller
├── service
├── repository
├── entity
├── dto
└── mapper
```

---

# 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation

---

# 📚 Learning Roadmap

| Level | Module | Concepts |
|------:|--------|----------|
| ⭐ 1 | Student Management | CRUD, Entity, Repository |
| 2 | Todo App | Enum, Status Update |
| 3 | Employee Management | Validation, Search |
| 4 | Library Management | Filtering & Search |
| 5 | Product Inventory | Stock Management |
| 6 | Contact Book | Custom Queries |
| 7 | Blog API | Relationships |
| 8 | Movie Collection | Pagination & Sorting |

---

# ✨ Features

- RESTful APIs
- CRUD Operations
- Layered Architecture
- Validation
- Global Exception Handling
- Search APIs
- Pagination
- Sorting
- Modular Project Structure
- MySQL Integration

---

# ⚙️ Getting Started

## Clone the repository

```bash
git clone https://github.com/<your-username>/SpringVerse.git
```

## Navigate into the project

```bash
cd SpringVerse
```

## Configure Database

Update your `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springverse
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Run the application

```bash
mvn spring-boot:run
```

---

# 📌 API Modules

```
/students
/employees
/books
/tasks
/products
/contacts
/posts
/movies
```

---

# 🎯 Goals

- Learn Spring Boot step-by-step
- Build REST APIs
- Understand Spring Data JPA
- Learn clean architecture
- Practice backend development
- Build a strong portfolio project

---

# 🤝 Contributing

Contributions are always welcome!

If you'd like to improve SpringVerse:

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Open a Pull Request

---

# ⭐ Show Your Support

If you found this repository helpful:

⭐ Star the repository

🍴 Fork it

📢 Share it with others

---

<div align="center">

Made with ❤️ using Spring Boot

**Happy Coding! 🚀**

</div>
