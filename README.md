# World Cup Scoreboard

This project provides a Java-based application to manage live World Cup match scores, tracking scores, and team participation. The project is designed as a Spring-based library, utilizing dependency injection and repository pattern principles for flexibility.

### Table of Contents
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [Further Improvements](#further-improvements)

### Getting Started

#### Prerequisites
- Java 17 or above
- Gradle (for building the project)
- Spring Framework (included via Gradle dependencies)

#### Installation
Clone the repository and build the project:
```bash
git clone <repo-url>
cd <project-directory>
gradlew clean build
```

#### Project Structure
- configuration: Contains the Spring configuration for the application.
- exceptions: Defines custom exception classes for handling invalid operations.
- model: Represents core classes for a match, teams, and scoring.
- repository: Contains the repository interface and an in-memory implementation for storing matches.
- service: Provides the main ScoreBoardService class for match operations.

#### Usage
- Starting a Match
  - To start a match between two teams:

    ```java
    TeamPair teamPair = new TeamPair(WorldCupTeams.ARGENTINA, WorldCupTeams.BRAZIL);
    scoreBoardService.startMatch(teamPair);
    ```
- Updating a Score
  - Update the score of an ongoing match:

    ```java
    scoreBoardService.updateScore(teamPair, 2, 1);
    ```
- Finishing a Match
  - To end a match:

    ```java
    scoreBoardService.finishMatch(teamPair);
    ```
- Printing the Summary
  - Display a nicely formatted scoreboard:
    ```java
    scoreBoardService.printSummary();
    ```

#### Further Improvements
To make the project more scalable and extensible, consider the following:

- Handling Draws and Penalties: Add logic to handle match draws and penalty outcomes if required.
- Additional Features: Consider adding player statistics, match history, and points calculations.
- Database Support: Replace the in-memory repository with a database implementation to persist matches.
- Event Logging: Introduce logging to track important actions and exceptions within the service.
- Asynchronous Processing: Use asynchronous methods to handle match updates if a high number of matches are tracked concurrently.