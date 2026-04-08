# Bookstore Management System

A desktop bookstore management application built with Java and JavaFX. The app supports two user roles — **Owner** and **Customer** — each with their own dashboard and set of features, accessible through a role-based login system.

## Features

### Authentication
- Login screen with username and password validation
- Role-based routing: Owner and Customer are directed to separate dashboards upon login

### Owner Dashboard
- **Book Management** — View and manage the bookstore's inventory
- **Customer Management** — View and manage registered customer accounts
- Logout functionality to return to the login screen

### Customer Dashboard
- Browse available books with pricing
- Select books for purchase using a checkbox-based UI
- Account-specific session tied to the logged-in username

## Tech Stack

- **Language:** Java
- **UI Framework:** JavaFX
- **Serialization:** Java `Serializable` for data persistence

## Project Structure

| File | Description |
|---|---|
| `FinalProject.java` | Application entry point; renders the login screen |
| `Login.java` | Handles credential validation and role detection |
| `Book.java` | Book model (name, price, checkbox UI element) |
| `BookStore.java` | Manages the collection of books |
| `Customer.java` | Customer model |
| `CustomerStore.java` | Manages the collection of customers |
| `OwnerScreen.java` | Owner dashboard with navigation to book/customer management |
| `OwnerBookScreen.java` | Owner view for managing books |
| `OwnerCustomerScreen.java` | Owner view for managing customers |
| `CustomerScreen.java` | Customer-facing shopping interface |

## Getting Started

### Prerequisites
- Java 8 or higher
- JavaFX SDK (included in JDK 8; separate download for JDK 11+)
- An IDE such as IntelliJ IDEA or NetBeans (recommended), or the command line

### Running the App

1. Clone the repository:
   ```bash
   git clone https://github.com/gabtlv/Bookstore-App.git
   ```

2. Open the project in your IDE and ensure JavaFX is configured on the classpath.

3. Run `FinalProject.java` as the main class.

> If using JDK 11+, add the JavaFX modules to your VM options:
> ```
> --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
> ```

## Author

Gab — [github.com/gabtlv](https://github.com/gabtlv)
