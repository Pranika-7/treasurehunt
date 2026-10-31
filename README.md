# Multiplayer Treasure Hunt Game

A multiplayer Treasure Hunt game built with Java and Spring Boot. Multiple players connect to a central game server, explore a virtual map, collect treasures, and earn points.

## Technologies Used
* Java 17+
* Spring Boot (Web, Data JPA)
* MySQL
* Maven

## Features
* Concurrent multiplayer gameplay.
* Thread-safe treasure collection using database-level atomic updates.
* Persistent player scores and leaderboard.

Multiplayer Treasure Hunt Game 

A standalone Spring Boot application that manages a concurrent multiplayer treasure hunt game. Multiple players connect to a central server, explore a virtual map, collect treasures, and earn points.

Tech Stack

Java (JDK 17+)

Spring Boot 3.2.0 (Web, Data JPA)

MySQL 8.0+ (Database)

Maven Wrapper (Build tool)

Architecture Overview

This project follows a standard layered Spring Boot architecture:

Model Layer (/model): Defines the Player and Treasure database entities.

Repository Layer (/repository): Interfaces with MySQL using Spring Data JPA.

Concurrency Handling: TreasureRepository utilizes a custom @Modifying query to perform atomic database updates. This guarantees thread-safe treasure collection without relying on heavy application-level locking.

Service Layer (/service): Contains the core business logic, transactional boundaries, and randomized map generation.

Controller Layer (/controller): Exposes the REST APIs for external interaction.

Prerequisites & Database Setup

Before running the application, ensure MySQL is running and create the database:

CREATE DATABASE IF NOT EXISTS treasure_hunt_db;


Update your MySQL credentials in src/main/resources/application.properties if necessary:

spring.datasource.username=root
spring.datasource.password=your_password


How to Build and Run

This project uses the Maven Wrapper, so you do not need Maven installed globally on your system.

1. Clean and Build the Project:
Open your terminal at the root of the project (where pom.xml is located) and run:

./mvnw clean package


2. Start the Server:
Once the build is successful, execute the generated JAR file:

java -jar target/treasure-hunt-0.0.1-SNAPSHOT.jar


The application will start on http://localhost:8080.

API Endpoints & Testing Guide

You can test the game flow using a REST client like Postman, or directly from a terminal using the following curl commands:

1. Register a new player:

curl -X POST http://localhost:8080/api/players?name=Player1


2. Start a new game (Spawns 10 treasures on the map):

curl -X POST http://localhost:8080/api/game/start


3. View the map (Get list of uncollected treasures):

curl http://localhost:8080/api/game/map


(Note the id of a treasure from the response output for the next step).

4. Collect a treasure:
Replace {treasureId} with an ID from the map, and {playerId} with your registered player ID.

curl -X POST "http://localhost:8080/api/game/collect/1?playerId=1"


5. View the Leaderboard:

curl http://localhost:8080/api/leaderboard


6. View a specific player's score:

curl http://localhost:8080/api/players/1/score
