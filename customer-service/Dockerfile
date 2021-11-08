# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

# LABEL maintainer="swucs7@gmail.com"
ENV TZ=Asia/Seoul
# VOLUME /tmp
EXPOSE 9991
ARG MODULE=shinyoung-customer-service
ARG JAR_FILE=${MODULE}/target/shinyoung-customer-service.jar
#ARG JAR_FILE=target/shinyoung-customer-service.jar
ADD ${JAR_FILE} shinyoung-customer-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/shinyoung-customer-service.jar","--spring.profiles.active=real", "--eureka.client.service-url.defaultZone=http://sy-discovery-server:8761/eureka", "--spring.config.import=optional:configserver:http://sy-config-server:8888"]
