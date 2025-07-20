# Docker Compose

- [Docker-compose Spec.](https://docs.docker.com/reference/compose-file/)
- [Compose Application Model](https://docs.docker.com/compose/intro/compose-application-model/)

## Commands
- **Base:** docker compose \[OPTIONS] CMD
  - up/down
    - `-d` - run in background (daemon)
    - `-f <custom-name>.yaml`
  - ps ∙∙∙∙∙∙∙∙∙∙∙ logs

## compose.yaml (docker-componse.yaml)

- Variable Substitution `${}`, e.g. ubuntu:${TAG:-latest}
  - Def. after `:-`, i.e. 'latest', use TAG env if provided
  - Note: The var is pulled from env of local (host w/ docker engine running)

```yaml
services:
  <app-name>:
    image: nginx:${TAG:-latest}
    ports:
    - "8080:80"
    volumes:
    - ./web:/usr/share/nginx/html:ro
  <app-2>:
    image: ubuntu
    depends_on:
    - <app-name>
    command: "curl http://<app-name>"
    env_file:
    - app.env
    environment:
    - app.name=<app-2>
    - INPUT=${INPUT:-42}
```

## Environment File, e.g. app.env
- Key: services.\<app>.env_file (arr)

```properties
R1=Kanto
R2=Jhoto
```