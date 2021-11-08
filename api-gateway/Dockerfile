# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

# LABEL maintainer="swucs7@gmail.com"
ENV TZ=Asia/Seoul
# VOLUME /tmp
EXPOSE 8000
ARG MODULE=shinyoung-api-gateway
ARG JAR_FILE=${MODULE}/target/shinyoung-api-gateway.jar
#ARG JAR_FILE=target/shinyoung-api-gateway.jar
ADD ${JAR_FILE} shinyoung-api-gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/shinyoung-api-gateway.jar","--eureka.client.service-url.defaultZone=http://sy-discovery-server:8761/eureka","--spring.cloud.gateway.globalcors.corsConfigurations.'[/**]'.allowedOrigins=http://web.sungbae.net:3001/"]
#spring.cloud.gateway.globalcors.cors-configuration./**.allowed-origins
#"--spring.cloud.gateway.globalcors.corsConfigurations.'[/**]'.allowedOrigins=http://sy-wms-ui:3000"
