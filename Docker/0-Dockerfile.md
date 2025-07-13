# Dockerfile

- **Custom Image:** docker build -t \<image> \<dockerfile-dir> 

## Dockerfile Commands
- FROM \<image>
- WORKDIR \<path>
- ADD/COPY \<host-dir> \<container-dir>
    - COPY src dest
    - COPY src/*.txt dest/
- RUN \<command>
- ENV \<key> \<value>
- EXPOSE \<port>
- CMD/ENTRYPOINT \<command>
    - Shell form: `CMD echo $PATH`
    - Exec form: `CMD [ "echo", "$PATH" ]`... limitations since does NOT run in shell, e.g. no env variable interpretation

## Demo: Create JDK Ubuntu Image

- `docker build -t jdk-21-ubuntu .`

- https://adoptium.net/temurin/releases
- https://adoptium.net/download?link=https%3A%2F%2Fgithub.com%2Fadoptium%2Ftemurin21-binaries%2Freleases%2Fdownload%2Fjdk-21.0.7%252B6%2FOpenJDK21U-jdk_x64_linux_hotspot_21.0.7_6.tar.gz&vendor=Adoptium

- apt-get update && apt-get install curl -y
- uname -m   (display architecture)
- curl \<url> --output java21.tar.gz -L
    - Linux - https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.7%2B6/OpenJDK21U-jdk_x64_linux_hotspot_21.0.7_6.tar.gz
- tar -xf java21.tar.gz
- rm java21.tar.gz
- export PATH=$PATH:jdk-21.0.7+6/bin


```dockerfile
FROM ubuntu

WORKDIR /home/public

RUN apt-get update && apt-get install curl -y
RUN curl https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.7%2B6/OpenJDK21U-jdk_x64_linux_hotspot_21.0.7_6.tar.gz --output java21.tar.gz -L
RUN tar -xf java21.tar.gz
RUN rm java21.tar.gz

ENV PATH=$PATH:/home/public/jdk-21.0.7+6/bin
```