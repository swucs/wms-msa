# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

# LABEL maintainer="swucs7@gmail.com"
ENV TZ=Asia/Seoul
# VOLUME /tmp
EXPOSE 9991

ARG MODULE=shinyoung-config-server
#config파일을 복사
ADD ${MODULE}/target/classes/config  /config
ARG JAR_FILE=${MODULE}/target/shinyoung-config-server.jar
#COPY target/classes/config  /app/config
#ARG JAR_FILE=target/shinyoung-config-server.jar
ADD ${JAR_FILE} shinyoung-config-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/shinyoung-config-server.jar","--spring.cloud.config.server.git.uri=/app/config", "--eureka.client.service-url.defaultZone=http://sy-discovery-server:8761/eureka"]
