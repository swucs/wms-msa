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