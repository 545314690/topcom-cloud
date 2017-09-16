"# eureka-server" 
#1.build project 
mvnw install dockerfile:build

#2.tag project
docker tag lisenmiao/eureka-server lisenmiao/eureka-server:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/eureka-server:latest

#5.pull form docker hub
docker pull lisenmiao/eureka-server

#6.run app
docker run -d -p 1111:1111 -t lisenmiao/eureka-server --name eureka-server