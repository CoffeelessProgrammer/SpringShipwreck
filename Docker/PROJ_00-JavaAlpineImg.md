# Project: Create Java Alpine Image

## Env Setup Notes
- uname -m   (display architecture)
- ~~apk update~~
- wget -O java21.tar.gz \<url>
    - Alpine - https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.7%2B6/OpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.7_6.tar.gz
- tar -xf java21.tar.gz
- rm java21.tar.gz
- export PATH=$PATH:/home/public/jdk-21.0.7+6-jre/bin

## Dockerfile

- `docker build -t jre-21-alpine .`

```dockerfile
FROM alpine:latest

WORKDIR /home/public

ADD https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.7%2B6/OpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.7_6.tar.gz java21.tar.gz
RUN tar -xf java21.tar.gz
RUN rm java21.tar.gz

ENV PATH=$PATH:/home/public/jdk-21.0.7+6-jre/bin
```

### Image Size Reference

```text
REPOSITORY      TAG       IMAGE ID       CREATED          SIZE
jdk-21-ubuntu   latest    449288e97e36   24 seconds ago   1.2GB
jre-21-alpine   latest    c590c0790f2e   6 minutes ago    333MB
ubuntu          latest    440dcf6a5640   3 weeks ago      117MB
alpine          latest    8a1f59ffb675   6 weeks ago      12.8MB
```
_____

## Challenges Encountered
1. **Context:** Unzipping JDK tar archive<br>**Error:** tar: invalid magic<br>**Obs:** Incorrect data being fetched (via wget) using Adoptium download [link](https://adoptium.net/download?link=https%3A%2F%2Fgithub.com%2Fadoptium%2Ftemurin21-binaries%2Freleases%2Fdownload%2Fjdk-21.0.7%252B6%2FOpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.7_6.tar.gz&vendor=Adoptium), i.e. html instead of tar.
    - **Solution:** Use source tar archive link directly from Github release. [Temurin 21 Binaries | GitHub](https://github.com/adoptium/temurin21-binaries/releases)
    - **!REF** https://unix.stackexchange.com/questions/302192/how-to-solve-tar-invalid-magic-error-on-linux-alpine