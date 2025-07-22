# Banking App

A Spring Boot RESTful API for banking operations built with Java 21, Spring Boot 3.5.3, and MySQL.

## Features

- **Account Management**: Create, read, update, and delete bank accounts
- **Transactions**: Deposit and withdraw money from accounts
- **Data Validation**: Input validation and error handling
- **Database Integration**: MySQL database with JPA/Hibernate
- **Testing**: Comprehensive unit and integration tests
- **CI/CD**: Automated testing with GitHub Actions

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **JUnit 5 & Mockito**
- **Maven**

## API Endpoints

### Account Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts` | Create a new account |
| GET | `/api/accounts/{id}` | Get account by ID |
| GET | `/api/accounts` | Get all accounts |
| PUT | `/api/accounts/{id}/deposit` | Deposit money |
| PUT | `/api/accounts/{id}/withdraw` | Withdraw money |
| DELETE | `/api/accounts/{id}` | Delete account |

### Example Requests

#### Create Account
```json
POST /api/accounts
{
  "accountHolderName": "John Doe",
  "balance": 1000.0
}
```

#### Deposit Money
```json
PUT /api/accounts/1/deposit
{
  "amount": 500.0
}
```

#### Withdraw Money
```json
PUT /api/accounts/1/withdraw
{
  "amount": 200.0
}
```

## Database Setup

1. Install MySQL
2. Create database:
   ```sql
   CREATE DATABASE banking_db;
   ```
3. Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Running the Application

### Prerequisites
- Java 21
- MySQL

### Steps

1. Clone the repository
2. Configure database settings in `application.properties`
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

## Testing

### Run Unit Tests
```bash
./mvnw test
```

### Run with Coverage
```bash
./mvnw test jacoco:report
```

## CI/CD

The project includes GitHub Actions workflow that:
- Runs on every push and pull request
- Sets up Java 21 environment
- Caches Maven dependencies
- Executes all tests
- Generates test reports

## Project Structure

```
src/
├── main/
│   ├── java/com/practice/banking/
│   │   ├── controller/          # REST controllers
│   │   ├── service/             # Business logic
│   │   ├── repository/          # Data access layer
│   │   ├── entity/              # JPA entities
│   │   ├── dto/                 # Data transfer objects
│   │   ├── mapper/              # Entity-DTO mappers
│   │   └── BankingAppApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/practice/banking/
        ├── controller/          # Controller tests
        ├── service/             # Service tests
        └── BankingAppApplicationTests.java
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is for educational purposes.
