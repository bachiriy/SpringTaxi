# SpringTaxi - Taxi Management REST API

SpringTaxi is a RESTful API developed with **Spring Boot** to manage a taxi reservation platform. This solution aims to digitize the services of a taxi company, enabling efficient management of reservations, drivers, vehicles, and analytics.

---

## **Key Features**

### Reservations Management
- Create, update, delete, and view reservations.
- Price calculation based on distance (up to 100 km) and vehicle type.
- Check the availability of drivers and vehicles.
- Manage reservation statuses: `CREATED`, `CONFIRMED`, `COMPLETED`, `CANCELED`.

### Drivers Management
- Manage driver profiles: create, update, delete, and view.
- Handle driver availability (start and end times).
- Ensure a driver has only one active reservation at a time.

### Vehicles Management
- Manage vehicle details: create, update, delete, and view.
- Support for three vehicle types:
    - **BERLINE**: 4-5 seats, 5 MAD/km.
    - **VAN**: 7-9 seats, 7 MAD/km.
    - **MINIBUS**: More than 9 seats, 9 MAD/km.
- Ensure vehicles can have only one active reservation at a time.

### Analytics and Statistics
- Provide aggregated data for reservations, drivers, and vehicles:
    - **Reservations**:
        - Average price per kilometer.
        - Average trip distance.
        - Booking distribution by time slots.
        - Most requested geographical areas.
    - **Drivers**:
        - Driver occupancy rate (percentage of time on trips).
        - Availability analysis by time slots.
        - Status distribution (e.g., 10 AVAILABLE, 5 ON_TRIP, 2 UNAVAILABLE).
    - **Vehicles**:
        - Average mileage by vehicle type.
        - Usage rate by vehicle type.
        - Fleet status (e.g., 15 AVAILABLE, 8 ON_TRIP, 3 UNAVAILABLE).

---

## **Project Structure**
The project follows a clean and modular architecture:
- **Controller**: REST endpoints.
- **Service**: Business logic.
- **Repository**: Data access using Spring Data JPA.
- **DAO**: Complex queries and analytics.
- **Models**: Data entities.
- **DTOs**: Data Transfer Objects for APIs.
- **Mapper**: Object mapping between entities and DTOs.
- **Utils**: Utility classes.
- **Validation**: Data validation logic.
- **Exception Handling**: Centralized error management.
- **Testing**: Unit and integration tests.

---

## **Technologies and Tools**

### Core Frameworks and Tools
- **Spring Boot**: Application framework.
- **Spring Data JPA**: ORM for database operations.
- **Swagger**: API documentation.
- **Liquibase** (optional): Database migration management.

### Databases
- **H2**: Development environment.
- **MySQL**: QA environment.
- **PostgreSQL**: Production environment.

### Configuration
- Configuration via `application.yaml` profiles:
    - `application-dev.yaml` for development.
    - `application-qa.yaml` for QA.
    - `application-prod.yaml` for production.

### Testing
- **JUnit**: Unit tests.
- **Mockito**: Mocking framework for testing.

---

## **API Endpoints**

### Reservations
Base path: `/api/reservations`
- **CRUD Operations**:
    - `POST /api/reservations`: Create a reservation.
    - `GET /api/reservations/{id}`: Get a reservation by ID.
    - `PUT /api/reservations/{id}`: Update a reservation.
    - `DELETE /api/reservations/{id}`: Delete a reservation.
- **Analytics**:
    - `GET /api/reservations/analytics`: Return metrics such as average price per kilometer, trip distribution by time slots, etc.

### Drivers
Base path: `/api/drivers`
- **CRUD Operations**:
    - `POST /api/drivers`: Create a driver profile.
    - `GET /api/drivers/{id}`: Get a driver by ID.
    - `PUT /api/drivers/{id}`: Update a driver profile.
    - `DELETE /api/drivers/{id}`: Delete a driver profile.
- **Analytics**:
    - `GET /api/drivers/analytics`: Return metrics such as occupancy rate, availability by time slots, etc.

### Vehicles
Base path: `/api/vehicles`
- **CRUD Operations**:
    - `POST /api/vehicles`: Add a new vehicle.
    - `GET /api/vehicles/{id}`: Get vehicle details.
    - `PUT /api/vehicles/{id}`: Update vehicle details.
    - `DELETE /api/vehicles/{id}`: Remove a vehicle.
- **Analytics**:
    - `GET /api/vehicles/analytics`: Return metrics such as mileage and usage rate by type, fleet status, etc.

---

## **Best Practices Implemented**
- RESTful API design with appropriate HTTP methods: `GET`, `POST`, `PUT`, `DELETE`, `PATCH`.
- Exception handling and logging with **LOGGER**.
- Data validation for input requests.
- Transaction management for consistent data updates.
- Clean code principles with reusable components.

---

## **Getting Started**

1. Clone the repository:
   ```bash
   git clone https://github.com/bachiriy/SpringTaxi
   cd SpringTaxi
   ```

## **Swagger Api Docs**
    http://localhost:8080/swagger-ui
---
