README for ExpenseTracker Backend

PROJECT OVERVIEW

The Expense Tracker Backend is a robust REST API built with Spring Boot and MySQL.
It manages users and their expenses — enabling users to create accounts, add expenses, edit or delete them, and view spending summaries.
The backend acts as the core engine of the system, processing all business logic, storing data securely, and exposing endpoints for the frontend to interact with.

TECH STACK

* Spring Boot – Simplifies Java backend development and REST API creation

* MySQL – For storing user and expense data

* Spring Data JPA – Provides seamless database interaction

* Maven – Handles dependencies and build lifecycle

* JUnit & Mockito – For writing and running automated unit tests

KEY INSIGHTS

* Clean three-layer architecture (Controller → Service → Repository) keeps the code organized and scalable.

* Designed using RESTful conventions, making it easy to integrate with any frontend or mobile app.

* Uses path variables like /api/users/{userId}/expenses to associate expenses with specific users.

* Error handling ensures smooth user experience — avoiding crashes during invalid operations.

* Test-driven development with JUnit and Mockito improved reliability and reduced bugs early.

CONCLUSION

The Expense Tracker Backend provides a reliable and flexible API foundation for managing expenses.
Its modular design makes it easy to extend — such as adding authentication, categories, or analytics.
This backend demonstrates how a well-structured, tested, and scalable architecture can power a complete financial management system.