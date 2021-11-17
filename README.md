postgresql 설치
https://dejavuhyo.github.io/posts/install-postgresql-on-centos/

[root@localhost ~]# sudo su - postgres
[root@localhost ~]# psql


postgres 사용법
https://browndwarf.tistory.com/3


MSA 구성
Discovery server : http://localhost:8761/
config server : http://localhost:8888/sycoldstorage/dev

config 파일 refresh
http://localhost:9991/actuator/refresh (HTTP POST)

config Git repogitory
https://git.sungbae.net/shinyoung/wms-config.git

rest docs
mvn package로 생성해야 하는데, vm옵션으로 -D키=밸류 (복호화를 위한 키)를 설정해야 한다. 

패키지
.\mvnw clean compile package -DskipTests

docker build . --tag sy-custemer-service

docker run -d -p 9001:9001 sy-customer-service --name sy-customer-service --link sy-discovery-server:sy-discovery-server



//네트워크 생성 (api.sungbae.net 서버에 설정해야 함)
docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 shinyoung-network

//생성된 네트워크 정보 상세정보
docker network inspect shinyoung-network

//1.유레카
cd discovery-server

.\mvnw clean compile package -DskipTests=true

docker build . -t sy-discovery-server

#application.yml에 정의되어 있는 정보를 환경변수를 통해 변경할 수 있다. application.yml을 수정하지 않아도 된다.
docker run -d -p 8761:8761 --network shinyoung-network --name sy-discovery-server sy-discovery-server


//2.API-Gateway
cd api-gateway

.\mvnw clean compile package -DskipTests=true

docker build . -t sy-api-gateway

#application.yml에 정의되어 있는 정보를 환경변수를 통해 변경할 수 있다. application.yml을 수정하지 않아도 된다.
docker run -d -p 8000:8000 --network shinyoung-network --name sy-api-gateway sy-api-gateway


//3.Config-Server
cd config-server

.\mvnw clean compile package -DskipTests=true

docker build . -t sy-config-server

#application.yml에 정의되어 있는 정보를 환경변수를 통해 변경할 수 있다. application.yml을 수정하지 않아도 된다.
docker run -d -p 8888:8888 --network shinyoung-network --name sy-config-server sy-config-server


//4.customer-service
cd customer-service

.\mvnw clean compile package -DskipTests=true

docker build . -t sy-customer-service

#application.yml에 정의되어 있는 정보를 환경변수를 통해 변경할 수 있다. application.yml을 수정하지 않아도 된다.
docker run -d -p 9991:9991 --network shinyoung-network --name sy-customer-service sy-customer-service


-e spring.config.import="optional:configserver:http://sy-config-server:8888/"




.\mvnw clean compile package -DskipTests=true

docker build -t 태그명 .

//config
#application.yml에 정의되어 있는 정보를 환경변수를 통해 변경할 수 있다. application.yml을 수정하지 않아도 된다.
docker run -d -p 8888:8888 --network shinyoung-network
-e "eureka.client.service-url.defaultZone=http://유레카의네트워크명:8761/eureka"
-e "spring.profiles.active=real"
--name config-server 이미지명		


#Redis
docker run --name redis-container -p 6379:6379 -it -d --restart unless-stopped redis --requirepass qwer1324!
docker exec -it redis-container /bin/bash