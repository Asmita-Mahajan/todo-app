# API Documentation (Swagger / OpenAPI)

This project uses [springdoc-openapi](https://springdoc.org/) to auto-generate
interactive API documentation from the Spring MVC controllers.

## Stack

| Component | Version |
|-----------|---------|
| Spring Boot | 4.0.6 |
| Spring Framework | 7.0.7 |
| Java | 25 |
| springdoc-openapi (`springdoc-openapi-starter-webmvc-ui`) | 3.0.3 |
| OpenAPI spec | 3.1 |

> **Compatibility note:** springdoc **3.x** is required for Spring Boot 4 /
> Spring Framework 7. The older 2.x line calls a `ControllerAdviceBean(Object)`
> constructor that was removed in Spring 7 and fails at runtime with
> `NoSuchMethodError` when the OpenAPI document is generated.

## Accessing the docs

Start the app:

```bash
./mvnw spring-boot:run
```

Then open (default port `8080`):

| Resource | URL |
|----------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |
| OpenAPI YAML | http://localhost:8080/v3/api-docs.yaml |

## Configuration

Defined in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
# Swagger/OpenAPI settings
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

No `@Bean` / `OpenAPI` configuration class is required — springdoc scans the
`@RestController` classes automatically. To customise the title, description,
version, or add global metadata, add a config class:

```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI todoAppOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Todo App API")
                .description("REST API for managing users and tasks")
                .version("v1"));
    }
}
```

## Endpoints

### Tasks — `/api/tasks`

| Method | Path | Description | Request body | Response |
|--------|------|-------------|--------------|----------|
| `POST`   | `/api/tasks`      | Create a task            | `TaskDto` | `200` `TaskDto` |
| `GET`    | `/api/tasks`      | List all tasks           | —         | `200` `TaskDto[]` |
| `GET`    | `/api/tasks/{id}` | Get a task by id         | —         | `200` `TaskDto` / `404` |
| `PUT`    | `/api/tasks/{id}` | Update a task by id      | `TaskDto` | `200` `TaskDto` |
| `DELETE` | `/api/tasks/{id}` | Delete a task by id      | —         | `204 No Content` |

### Users — `/api/users`

| Method | Path | Description | Request body | Response |
|--------|------|-------------|--------------|----------|
| `POST`   | `/api/users`      | Create a user       | `UserDto` (validated) | `200` `UserDto` |
| `GET`    | `/api/users`      | List all users      | —                     | `200` `UserDto[]` |
| `GET`    | `/api/users/{id}` | Get a user by id    | —                     | `200` `UserDto` |
| `PUT`    | `/api/users/{id}` | Update a user by id | `UserDto` (validated) | `200` `UserDto` |
| `DELETE` | `/api/users/{id}` | Delete a user by id | —                     | `204 No Content` |

## Schemas

### TaskDto

| JSON field | Type | Constraints |
|------------|------|-------------|
| `task_id`     | number | — |
| `user_id`     | number | — |
| `title`       | string | required, max 100 chars |
| `description` | string | max 100 chars |
| `status`      | string | required |
| `priority`    | string | — |
| `due_date`    | string | — |

### UserDto

| JSON field | Type |
|------------|------|
| `user_id`       | number |
| `user_name`     | string |
| `password`      | string |
| `email`         | string |
| `created_date`  | string |
| `modified_date` | string |

## Enriching the generated docs (optional)

The endpoints above are discovered automatically. To make the Swagger UI more
descriptive, annotate controllers and DTOs with OpenAPI annotations:

```java
@Operation(summary = "Create a task", description = "Persists a new task and returns it")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Task created"),
    @ApiResponse(responseCode = "400", description = "Validation failed")
})
@PostMapping
public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) { ... }
```

```java
@Schema(description = "Task title", example = "Buy groceries", maxLength = 100)
private String title;
```

## Security note

`SecurityConfig` is currently commented out, so all endpoints — including the
Swagger UI and `/v3/api-docs` — are publicly accessible. If Spring Security is
later enabled, remember to permit the docs paths:

```java
.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
```
