
# Recipe Application

Recipe Application is a web service that contains REST APIs that allows adding, updating, removing and fetching recipes from the database and render the requested details as JSON response to end user. The response from REST APIs can be further integrated with front end view for better presentation.

## Technology Used

Spring Boot, Maven, H2 database, Validations, Junit, Swagger,
Java 1.8, Git

## System Design

Recipe Web Service is microservice based layered architectured RESTful Web Service. There are 4 layers from top to bottom:

- API Layer
  - Top layer, which is main interface available for intgeration and interaction with front-end or end user to consume APIs
  - Contains API end points implementation

- Service Layer
  - This layer sits in between API layer and Data access layer with some utility functionality
  - Mainly responsible for interacting with Data Access Layer and transferring the recipes and igredients data as required by top and below.

- Data Access Layer
  - Responsible to provide Object Relationship Mapping (ORM) between higher level recipe and ingredients Java objects and persistence layer tables.
  - This layer contains recipe and igredient entity classes and JPA repositories which implement lower level functionality of storing/retrieving recipes data.

- Persistence Layer
  - Bottom most layer, responsible for physically storing the recipes and ingrdients data onto database table.
  - Embedded H2 Database provided by Spring Boot framework is utilized.

## Build and Start the Application

#### Minimum Requirements

Java 1.8

Maven 3.x

## Installation

Install recipe-app project with Java 1.8 and Maven installed.

Open the command line in the source code folder.

##### Build project with below command :

```
mvn clean package
```

##### Run the project using below command :

```
$ java -jar target/recipe-app-0.0.1-SNAPSHOT.jar
```

##### Open the swagger-ui with the below link :

```
http://localhost:8081/swagger-ui.html#/
```

##### Open the H2 database with the below link 

```
http://localhost:8081/h2-console
```

