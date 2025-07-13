# Docker Basics

## Pushing to Docker Hub
- docker login/logout
- docker tag \<local-image:tagname> \<new-repo:tagname>
- docker push \<new-repo:tagname>

## Commands
- docker help ∙∙∙∙∙∙∙∙∙∙∙ docker \<CMD> --help ∙∙∙∙∙∙∙∙∙∙∙ docker/docker-compose version
- docker system prune -af (a = all imgs, not just dangling; f = no confirmation)
- docker images
- docker pull/run/rmi \<image> ∙∙∙∙∙∙∙∙∙∙∙ docker run -it \<image>
- **List Containers:** docker ps -a
- docker run -d --name=c1 ∙∙∙∙∙∙∙∙∙∙∙ docker start -ia c1 ∙∙∙∙∙∙∙∙∙∙∙ docker stop/kill \<container>
- docker exec -it \<container> \[cmd]
- **Port Mapping:** docker run -p host-port-1:container-port-1 \[-p host-port-2:container-port-2...] \<image>
- **Output Logs:** docker logs \<container-id>
- **Volume Mapping:** docker run -v /host-path:/container-path\[:ro] \[-v /host-path:/container-path...] \<image>
    - docker run -it -v "$(pwd):/a/b/c" ubuntu
- **Env Variables:** docker run -e sampleinput=7 \[-e var2=value2...] \<image>
- **Networking (Bridge):** docker network ls
- docker network create \<name>
- docker run --network=name alpine
- docker inspect \<container>