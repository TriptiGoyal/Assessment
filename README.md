
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

# Installation

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

## REST APIs

The REST APIs will return proper response code in the case of success or error cases.
Following are the HTTP response codes that will be returned from the APIs :
200-OK, 201-CREATED, 400-BAD REQUEST, 404-NOT FOUND, 500-INTERNAL SERVER ERROR

#### Below are API urls :

##### To fetch all ingredients :

curl --location --request GET 'http://localhost:8081/api/v1/ingredients/' \
--header 'Accept: application/json'

##### To Add new Ingredient :

curl --location --request POST 'http://localhost:8081/api/v1/ingredients/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "ingredientName": "Tomatoes",
  "ingredientQuantity": "1 Kg"
}'

##### To Delete an Ingredient :

curl --location --request DELETE 'http://localhost:8081/api/v1/ingredients/?ingredientId=7' \
--header 'Accept: application/json'

##### To fetch Ingredient By ID :

curl --location --request GET 'http://localhost:8081/api/v1/ingredients/8' \
--header 'Accept: application/json'

##### To fetch all recipes :

curl -i -H 'Accept: application/json' http://localhost:8081/api/v1/recipes

##### To fetch recipe by ID :

curl --location --request GET 'http://localhost:8081/api/v1/recipes/1' \
--header 'Accept: application/json'

##### To Add new recipe :

curl --location --request POST 'http://localhost:8081/api/v1/recipes/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "ingredientIds": [
    1,
    2
  ],
  "name": "Mix Vegetable",
  "recipeInstructions": "Chop the onion, potato and tomato, stir and fry, ready to serve",
  "recipeType": "Vegetarian",
  "servingCapacity": 4
}'

##### To Delete Recipe By ID :

curl --location --request DELETE 'http://localhost:8081/api/v1/recipes?id=5' \
--header 'Accept: application/json'

##### To Update Recipe  :

curl --location --request PATCH 'http://localhost:8081/api/v1/recipes' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "ingredientIds": [
    3,
    4
  ],
  "name": "Potato",
  "recipeId": 1,
  "recipeInstructions": "Chop the potatoes and deep fry, Ready to serve !!",
  "recipeType": "VEGETARIAN",
  "servingCapacity": 7
}'

##### To Search Recipe :

curl --location --request POST 'http://localhost:8081/api/v1/recipes/search' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "criteria": [
    {
      "filterParamKey": "name",
      "operation": "nc",
      "searchValue": "Pasta"
    }
  ],
  "filterOption": "all"
}'
