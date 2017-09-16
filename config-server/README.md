"# config-server" 
#1.build project 
mvnw install dockerfile:build

#2.tag project
docker tag lisenmiao/config-server lisenmiao/config-server:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/config-server:latest

#5.pull form docker hub
docker pull lisenmiao/config-server

#6.run app
docker run -d -p 7001:7001 -t lisenmiao/config-server --name config-server