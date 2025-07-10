# Docker for Spring
- [Docker](https://www.docker.com/get-started/)
- [Docker Hub](https://hub.docker.com/search?operating_system=linux&type=image&badges=official)

**Image Name:** \[registry-host:port] / \[username] / image-name \[:tag]
  - e.g. docker.io/library/hello-world:latest

## Basic Commands
- docker system prune -af
- docker images
- docker pull/run/rmi \[image-name] ∙∙∙∙∙∙∙∙∙∙∙ docker run -it \[image-name]
- docker ps -a
- docker exec -it \[container-name] bash
- docker run -p host-port-1:container-port-1 \[-p host-port-2:container-port-2] \[image-name]

## Course - Docker Masterclass For Java Spring Boot Developers
- Instructor Repo - [vinsguru/docker-spring-webflux](https://github.com/vinsguru/docker-spring-webflux)