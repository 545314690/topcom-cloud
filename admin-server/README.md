"# admin-server" 
#1.build project 
mvnw install dockerfile:build

#2.tag project
docker tag lisenmiao/admin-server lisenmiao/admin-server:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/admin-server:latest

#5.pull form docker hub
docker pull lisenmiao/admin-server

#6.run app
docker run -d -p 8080:8080 -t lisenmiao/admin-server --name admin-server