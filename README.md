# Challenge Back-end
This project is a REST API built with Spring Boot (Java 21) that performs dynamic percentage calculations, caches results, and logs request history asynchronously. It is designed for efficient computation and data storage using PostgreSQL and Docker.

## Features
- **Calculation API**: Perform percentage-based calculations from an external API (mock with a constant value).
- **Logs API**: Retrieve and manage logs of API requests and responses.
- **Caching**: Uses Caffeine for caching percentage values.
- **Database Integration**: Uses PostgreSQL for data persistence.

## Prerequisites
* Java 21
* Maven
* Docker
* PostgreSQL

### Clone the Repository
Clone the repository by using its public URL with the following command in your terminal: `git clone https://github.com/acones96/Backend_Challenge.git`.
This will create a local copy of the repository on your machine.

### Database Configuration

The application uses PostgreSQL and his default configuration is:

* **URL:** `jdbc:postgresql://localhost:5432/tekton`
* **Username:** `postgres`
* **Password:** `admin`

But it can be modified in the `application.properties` file.

### Docker
Run the containers using his docker-compose.yml. To run use the following steps and the application and postreSQL will be running.

1. Build maven project: `mvn clean package`
2. Build the Docker image: `docker-compose build`
3. Start the service: `docker-compose up`

The application will be available at `http://localhost:8080`


The [docker-compose.yml](docker-compose.yml) file set the following services:
* **challenge-app:** The Spring Bot application.
* **Postgres:** The PostgresSQL database.

## Swagger
To be able to see if the Swagger requests, you can go to the [swagger](http://localhost:8080/swagger-ui/index.html#/) application site.

## API Endpoints
There are two main endpoints:
1. `/api/calculate`: Located in [CalculationController](src/main/java/com/tekton/challenge_backend/controller/CalculationController.java).
2. `/api/history`: Located in [LogsController](src/main/java/com/tekton/challenge_backend/controller/LogsController.java).

> You can find the collection and the environment import files for these in the [postman resources](src/main/resources/postman).

### Calculation API for Postman
* **POST `http://localhost:8080/api/calculate`:** Perform a percentage-based calculation.

**Request Body:**
```
{
    "num1": 10.0,
    "num2": 20.0
}
```

**Response:**
```
33.0
```

### Logs API for Postman
* **Get `http://localhost:8080/api/history?page=0&size=3`:** Retrieve logs of API requests and responses.

**Query Parameters:**
* **page (default: 0):** Set the page number.
* **size (default: 3):** Set the page size.

## Caching
The application uses Caffeine for caching the percentage value retrieved from the [PercentageClient](src\main\java\com\tekton\challenge_backend\api\PercentageClient.java).

