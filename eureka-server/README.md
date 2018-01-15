"# eureka-server" 
#1.build project 
./mvnw install -Dmaven.test.skip=true dockerfile:build

#2.tag project
docker tag limushui.top:8082/eureka-server limushui.top:8082/eureka-server:latest

#3.login docker hub
docker login limushui.top:8082 <br>
->input username ＆ password
#4.push to docker hub
docker push limushui.top:8082/eureka-server:latest
docker push 192.168.1.12:5000/eureka-server:latest
#5.pull form docker hub
docker pull limushui.top:8082/eureka-server

#6.run app
docker run -d -p 1111:1111 -t limushui.top:8082/eureka-server --name eureka-server
