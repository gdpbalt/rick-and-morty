# Rick And Morty

### Project description

This is a test project that propose two endpoints:

1. The request randomly generates a wiki about one character in the universe the animated series
   Rick & Morty:
   > http://localhost:6868/movie-character/random

2. The request takes a string as an argument, and returns a list of all characters whose
   name contains the search string in the universe the animated series
   Rick & Morty:

   > http://localhost:6868/movie-character/by-name?name=test

### Features
- Application synchronizes local database with remote API that available via next url: 
   > https://rickandmortyapi.com/documentation/#getallcharacters
- Synchronizing happens one per a day and get new information from remote API.
- All requests do from local database.
- You can use swagger documentation page via url:
   > http://localhost:6868/swagger-ui/#/

### Project architecture
1. Controllers - Presentation layer
2. Services - Application layer
3. DAO - Data access layer

### Technologies used in project
- Spring Boot 2.7, Spring Web, Spring Jpa
- PostgreSQL
- Java v.17
- Apache Maven v.3.8
- Docker, Docker compose

### For launch project
1. Install Docker Desktop and register on DockerHub

2. Run next command in terminal from `rickandmorty` directory:

   > mvn clean package

3. Run next command in terminal from the main directory:

   > docker-compose up --build

4. Open your browser on http://localhost:6868.
