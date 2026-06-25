# Todo App — Backend

A REST API for managing users and their to-do tasks, built with Spring Boot 4 and MySQL.

## Tech Stack

- **Java 25**
- **Spring Boot 4.0.6** — Web MVC, Data JPA, Validation
- **MySQL 8** (via `mysql-connector-j`)
- **MapStruct 1.5.5** — entity ↔ DTO mapping
- **Lombok** — boilerplate reduction
- **Maven** — build tooling (Maven Wrapper included)

## Project Structure

```
src/main/java/com/todoapp/backend/
├── BackendApplication.java     # Spring Boot entry point
├── controller/                 # REST endpoints (TaskController, UserController)
├── service/                    # Business logic (TaskService, UserService)
├── repository/                 # Spring Data JPA repositories
├── model/                      # JPA entities (Task, User) and enums (TaskStatus, TaskPriority)
├── dto/                        # API request/response objects (TaskDto, UserDto)
├── mapper/                     # MapStruct mappers (TaskMapper, UserMapper)
└── config/                     # Configuration (SecurityConfig, RepositoryConfig)
```

## Prerequisites

- JDK 25
- MySQL 8 running locally
- A schema named `todoapp` (Hibernate creates tables automatically via `ddl-auto=update`)

```sql
CREATE DATABASE todoapp;
```

## Configuration

Database settings live in [application.properties](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Update the username/password to match your local MySQL setup.

## Running the App

```bash
# Run with the Maven wrapper
./mvnw spring-boot:run

# Or build a jar and run it
./mvnw clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

The app starts on `http://localhost:8080` by default.

## Running Tests

```bash
./mvnw test
```

## Data Model

### Task
| Field        | Type           | Notes                                    |
|--------------|----------------|------------------------------------------|
| `task_id`    | long           | Primary key, auto-generated              |
| `user_id`    | long           | Owning user (many tasks → one user)      |
| `title`      | String         | Required, max 100 chars                  |
| `description`| String         | Max 100 chars                            |
| `status`     | TaskStatus     | `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED` |
| `priority`   | TaskPriority   | `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`      |
| `due_date`   | String         | Due date                                 |

### User
| Field           | Type   | Notes                              |
|-----------------|--------|------------------------------------|
| `user_id`       | long   | Primary key, auto-generated        |
| `user_name`     | String | Unique, max 20 chars               |
| `password`      | String | Write-only (never serialized)      |
| `email`         | String | Required, max 30 chars             |
| `created_date`  | Date   | Auto-set on creation               |
| `modified_date` | Date   | Auto-updated on change             |

## API Endpoints

### Tasks — `/api/tasks`

| Method   | Path             | Description                |
|----------|------------------|----------------------------|
| `POST`   | `/api/tasks`      | Create a task              |
| `GET`    | `/api/tasks`      | List all tasks             |
| `GET`    | `/api/tasks/{id}` | Get a task by ID           |
| `PUT`    | `/api/tasks/{id}` | Update a task              |
| `DELETE` | `/api/tasks/{id}` | Delete a task              |

**Example — create a task**

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "user_id": 1,
    "title": "Write README",
    "description": "Document the backend API",
    "status": "PENDING",
    "priority": "HIGH",
    "due_date": "2026-07-01"
  }'
```

### Users — `/api/user`

| Method | Path         | Description       |
|--------|--------------|-------------------|
| `POST` | `/api/users`  | Create a user     |
| `GET`  | `/api/users`  | List all users    |

**Example — create a user**

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "user_name": "harshal",
    "password": "secret",
    "email": "harshal@example.com"
  }'
```

## Notes

- Security is currently disabled — `SecurityConfig` is commented out, so all endpoints are open. Add authentication before deploying.
- `ddl-auto=update` lets Hibernate manage the schema during development. Use a migration tool (e.g. Flyway/Liquibase) for production.

- those mapStatus and mapPriority methods are actually invoked depends on how MapStruct resolves type conversions
MapStruct automatically looks for matching methods in the mapper interface .
In your case:
Task.status is of type TaskStatus (enum).
TaskDto.status is of type String.
Since the types don’t match, MapStruct searches for a conversion method.
It finds default String mapStatus(TaskStatus status) and default TaskStatus mapStatus(String status) in the same interface.
✅ These methods will be used automatically for the mapping.
Same logic applies for priority enum.
