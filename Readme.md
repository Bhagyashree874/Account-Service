# **Account Service API**

## **Overview**

The Account Service API is a RESTful API built using Spring Boot that provides account management functionality. The API allows users to create new accounts, retrieve existing accounts, and perform other account-related operations.

### Features

Create new accounts with initial credit
Retrieve accounts by customer ID
Implementing resilience and fault tolerance using Resilience4j
Secured using OAuth2 and JWT

### Technology Stack

Spring Boot 3.3.1
Java 22
Maven 4.0.0
H2 Database
Lombok
Resilience4j
OAuth2 and JWT for security

### Project Structure

The project is structured as follows:

com.bhagya.account.api: The main package for the API
config: Configuration classes for the API
controller: REST controllers for the API
model: Data models for the API
repository: Data access objects for the API
service: Business logic for the API
com.account.api.test: Test classes for the API

### Getting Started

To get started with the project, follow these steps:

Clone the repository
Run mvn clean package to build the project
Run java -jar target/account-service-1.0-SNAPSHOT.jar to start the API
Use a tool like Postman or cURL to test the API endpoints

### API Endpoints

#### Create Account

POST /accounts: Create a new account with initial credit
Request Body: CreateAccountRequest object
Response: Account object

#### Get Accounts

GET /accounts/{customerId}: Retrieve accounts by customer ID
Path Variable: customerId
Response: List of Account objects

### Security

The API is secured using OAuth2 and JWT. To access the API endpoints, you need to obtain a JWT token and include it in the Authorization header of your requests.

### Resilience and Fault Tolerance

The API is designed to be resilient and fault-tolerant using Resilience4j. This includes features like circuit breakers, retries, and fallbacks to handle failures and exceptions.

### Testing

The project includes unit tests and integration tests using JUnit and Mockito. To run the tests, use the command mvn test.