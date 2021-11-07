# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

# LABEL maintainer="swucs7@gmail.com"
ENV TZ=Asia/Seoul
# VOLUME /tmp
EXPOSE 9991
ARG MODULE=shinyoung-config-server
#ARG JAR_FILE=${MODULE}/build/libs/shinyoung-config-server.jar
ARG JAR_FILE=target/shinyoung-config-server.jar
ADD ${JAR_FILE} shinyoung-config-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/shinyoung-config-server.jar","--spring.profiles.active=real"]
