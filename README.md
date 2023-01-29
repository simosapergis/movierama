# Tools used
- Java 17
- Spring Boot 3.0.2
- Postgres 13
- Thymeleaf
- HTMX
- Docker & Docker Compose
---

The application is containerized and in order to run it, 
docker and docker compose tools are required. 

**Note**: You don't need to clone the repo to run it, 
docker-compose.yml file pulls the required images form docker hub.

# Instructions to run the app with docker

1. Install docker 
https://docs.docker.com/get-docker/

2. Install docker compose
https://docs.docker.com/compose/install/

3. Download docker-compose.yml from this repo's root path.

4. Run (for linux)
`sudo docker-compose -f docker-compose.yml up`

5. When docker is finished and spring is up and running, 
go to http://localhost:8080/ and test the app.