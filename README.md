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