Guest-flow 
Overview
The Guestflow is a REST API web-based application built with Java 17 and the latest version of Spring Boot that allows users to create, manage, and track events.
Also, users can register attendees for events, view detailed event information, and track participation. The GuestFlow system uses a REST API for communication between the frontend and backend, and it persists event data in a relational database.

Table of Contents
Overview
Features
Architecture
Installation
Usage
API Endpoints
Technologies
Contributing
License


Features

Event Management: Create, update, and delete events.
Attendee Management: Register attendees for events.
Event Details: View detailed information for each event, including the event title, description, maximum attendees, and creation date.
Unique Slug Generation: This will automatically generate unique slugs for each event to create readable URLs.
Error Handling: Handles common errors such as missing data, invalid inputs, and database constraint violations, Resource Not Found (404), Method Not Allowed (405), Global Exception Handling, URI Validation, Custom Error Messages.
REST API: The project provides a clean and well-documented REST API for interaction with external systems.

Architecture
The application follows a typical multi-layer architecture:

Controller Layer: Handles incoming HTTP requests, processes them, and returns responses.
Service Layer: Contains the business logic and coordinates between the controller and repository layers.
Repository Layer: Interacts with the database using Spring Data JPA.
DTOs: Data Transfer Objects (DTOs) are used to map data between the application layers and the REST API.

Installation

Prerequisites
Java 17 or later
Maven
A relational database (hsqldb)
Spring Boot 3.x (latest version)

Setup Steps
Clone the repository:

bash
Copy code
git clone (https://github.com/olivejoys/guest-flow.git)
cd guest-flow
Configure the Database:

Create a database and configure the connection details in the application.properties (or application.yml) file:

properties
Copy code
spring.application.name=guest-flow
spring.datasource.driver-class-name=org.hsqldb.jdbc.JDBCDriver
spring.datasource.url=jdbc:hsqldb:file:src/main/resources/database/myDb;shutdown=true

Run the Application:

Use Maven to build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run

API Documentation 

Usage
Creating an Event
To create an event, send a POST request to the /events endpoint with the following JSON payload:

json
Copy code
{
  "title": "Event Title",
  "details": "Detailed event description",
  "maxAttendees": 100,
  "slug": "event-title-slug",
  "createdAt": "2024-09-03T12:00:00"
}
Registering an Attendee
To register an attendee, use the /events/{id}/attendees endpoint:

json
Copy code
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
Viewing Event Details
Send a GET request to /events/{id} to view event details:

json
Copy code
{
  "id": "event-id",
  "title": "Event Title",
  "details": "Detailed description",
  "slug": "event-title-slug",
  "maxAttendees": 100,
  "createdAt": "2024-09-03T12:00:00"
}
API Endpoints
Method	Endpoint	Description
GET	/events/{id}	Get details of a specific event
POST	/events	Create a new event
POST	/events/{id}/attendees	Register a new attendee for the event
GET	/attendees/{id}	Get list of attendees for a specific event
Technologies
Java 17
Spring Boot 3.x (latest version) 
Spring Data JPA for data persistence
Flyway for database migrations
Hibernate as the ORM framework
PostgreSQL (or another RDBMS) for the database
Maven for project build and dependency management

Contributing
Fork the repository.
Create a new feature branch (git checkout -b feature-branch).
Commit your changes (git commit -m 'Add new feature').
Push the branch (git push origin feature-branch).
Open a Pull Request.
