# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

# LABEL maintainer="swucs7@gmail.com"
ENV TZ=Asia/Seoul
# VOLUME /tmp
EXPOSE 8761
ARG MODULE=shinyoung-discovery-server
#ARG JAR_FILE=${MODULE}/build/libs/shinyoung-discovery-server.jar
ARG JAR_FILE=target/shinyoung-discovery-server.jar
ADD ${JAR_FILE} shinyoung-discovery-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/shinyoung-discovery-server.jar","--spring.profiles.active=real"]
