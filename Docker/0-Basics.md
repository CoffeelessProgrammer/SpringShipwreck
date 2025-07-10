# Docker Basics

## Basic Commands
- docker help ∙∙∙∙∙∙∙∙∙∙∙ docker CMD --help ∙∙∙∙∙∙∙∙∙∙∙ docker \[version] ∙∙∙∙∙∙∙∙∙∙∙ docker-compose \[version]
- docker system prune -af (a = all imgs, not just dangling; f = no confirmation)
- docker images
- docker pull/run/rmi \[image-name] ∙∙∙∙∙∙∙∙∙∙∙ docker run -it \[image-name]
- docker ps -a
- docker run --name=c1 ∙∙∙∙∙∙∙∙∙∙∙ docker start -ia c1 ∙∙∙∙∙∙∙∙∙∙∙ docker stop/kill \[container-id|name]
- docker exec -it \[container-name] \[cmd]
- docker run -p host-port-1:container-port-1 \[-p host-port-2:container-port-2] \[image-name]