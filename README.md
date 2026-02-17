# 🚗 Vehicle Management System (VMS)

A desktop-based Vehicle Management System developed using:

- Java
- Swing (UI)
- Hibernate ORM
- MySQL Database
- Maven Project Structure

This project follows clean architecture principles using layered design to ensure scalability, maintainability, and industry-standard coding practices.

---

# 📁 Project Structure

com.vms
│
├── config
├── model
├── dao
├── dao.impl
├── service
├── service.impl
├── controller
├── view
├── util
├── exception
└── main




---

# 🧩 Package Explanation

---

## ✅ config

Contains configuration-related classes.

### Responsibilities:

- Hibernate configuration setup
- SessionFactory initialization
- Database connection setup

### Example:

- `HibernateUtil.java`

---

## ✅ model

Contains entity classes mapped to database tables using Hibernate.

### Examples:

- `Vehicle` (abstract parent class)
- `Bike`
- `Car`
- `Bus`

### Responsibilities:

- Represent database structure
- Use JPA annotations:

@Entity
@Table
@Id
@GeneratedValue



---

## ✅ dao

Contains DAO interfaces defining database operations.

### Responsibilities:

- Abstract database operations
- Define CRUD methods

### Example:




---

## ✅ dao.impl

Contains implementations of DAO interfaces.

### Responsibilities:

- Hibernate session handling
- Transaction management
- Database interaction

### Example:



---

## ✅ service

Contains service interfaces.

### Responsibilities:

- Define business logic contracts
- Act as middle layer between Controller and DAO

Example:

---

## ✅ service.impl

Contains business logic implementation.

### Responsibilities:

- Validation logic
- Business rules
- DTO to Entity conversion (if DTO used)

Example:


---

## ✅ view

Contains Swing UI components.

### Responsibilities:

- User Interface screens
- Event handling
- Calling controller methods

Examples:

- DashboardFrame
- AddVehicleForm

---

## ✅ util

Helper classes used across the project.

### Examples:

- ValidationUtil
- DialogUtil
- DateUtil
- Constants

### Responsibilities:

- Common reusable functionality

---

## ✅ exception

Custom exception classes.

### Examples:

- VehicleNotFoundException
- ValidationException

### Responsibilities:

- Custom error handling
- Cleaner exception management

---

## ✅ main

Application entry point.

### Example:


### Responsibilities:

- Start application
- Launch UI

---

# 🏗 Architecture Flow



---

# 🚀 Features

- Abstract Vehicle class with inheritance (Bike, Car, Bus)
- Hibernate ORM mapping
- Clean layered architecture
- Industry-standard project structure

---

# 🔧 Setup Instructions

1. Clone repository
2. Setup MySQL database
3. Configure `hibernate.cfg.xml`
4. Run MainApp.java

---

# ⭐ Future Improvements

- DTO layer implementation
- Generic DAO pattern
- Dependency Injection
- Logging framework integration
- REST API version

---

# 👨‍💻 Author

Vehicle Management System built using clean architecture principles for scalable desktop application development.

