# Course Seat Watcher

Course Seat Watcher is a full-stack web application built with Java and Spring Boot that allows students to monitor course seat availability and receive email notifications when seats become available.

## Features

- Add, update, and delete courses
- Search for courses by course code
- View courses with available seats
- Watch or unwatch specific courses
- Automatically monitor seat availability in the background
- Detect when a course changes from closed to open
- Send email notifications when a watched course becomes available
- Store course data in PostgreSQL
- Manage courses through a web interface
- Unit testing with JUnit and Mockito

## Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Spring Mail
- JUnit 5
- Mockito
- Maven
- Git / GitHub
## Architecture

The application follows a layered Spring Boot architecture:

Browser / Thymeleaf UI  
↓  
Controller  
↓  
Service  
↓  
Repository  
↓  
PostgreSQL

A scheduled background service periodically checks watched courses for changes in seat availability. When a course changes from closed to open, the application sends an email notification.

### Main Components

- **CourseController** - Provides REST API endpoints for course operations
- **WebController** - Handles the Thymeleaf web interface
- **CourseService** - Contains course management logic
- **CourseMonitorService** - Periodically monitors watched courses for seat changes
- **EmailService** - Sends seat availability notifications
- **CourseRepository** - Provides database access using Spring Data JPA
## REST API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/courses` | Get all courses |
| GET | `/courses/{id}` | Get a course by ID |
| POST | `/courses` | Add a course |
| PUT | `/courses/{id}` | Update a course |
| DELETE | `/courses/{id}` | Delete a course |
| GET | `/courses/open` | Get courses with available seats |
| GET | `/courses/code/{courseCode}` | Search by course code |
| PUT | `/courses/{id}/watch` | Watch a course |
| PUT | `/courses/{id}/unwatch` | Stop watching a course |
## Testing

The service layer includes unit tests using JUnit 5 and Mockito.

Tests cover:

- Retrieving all courses
- Finding courses by ID
- Handling courses that do not exist
- Adding courses
- Updating courses
- Deleting courses
- Retrieving open courses
- Updating watch status

Run the tests with:

```bash
./mvnw test
```
## Configuration

The application uses environment variables to keep database and email credentials out of source control.

Required environment variables:

```text
DB_PASSWORD
MAIL_USERNAME
MAIL_PASSWORD
```
