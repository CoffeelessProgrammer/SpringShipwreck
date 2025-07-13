# Docker for Spring
- [Docker](https://www.docker.com/get-started/)
- [Docker Hub](https://hub.docker.com/search?operating_system=linux&type=image&badges=official)
- [Adoptium Temurin 21 Binaries | GitHub](https://github.com/adoptium/temurin21-binaries/releases)

**Image Name:** \[registry-host:port] / \[username] / image-name \[:tag]
  - e.g. docker.io/library/hello-world:latest

## Basic Commands
- docker system prune -af
- docker images ∙∙∙∙∙∙∙∙∙∙∙ docker network ls
- docker ps -a
- docker logs \<container-id>
- docker pull/run/rmi \<image> ∙∙∙∙∙∙∙∙∙∙∙ docker run -it \<image>
  - docker exec -it \<container> bash
- docker run -d -p 8080:80 \[-p host-port-2:container-port-2] nginx
- docker run -v c:/Users/Public:/home/public alpine
- docker run -e envvar1=7 my-java-app

### Build & Push
- docker build -t luffy/going-merry:v0 .
- docker tag going-merry luffy/going-merry
  - docker push luffy/going-merry

## Course - Docker Masterclass For Java Spring Boot Developers
- Instructor Repo - [vinsguru/docker-spring-webflux](https://github.com/vinsguru/docker-spring-webflux)