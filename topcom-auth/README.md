"# topcom-auth" 
#1.build project 
./mvnw install -Dmaven.test.skip=true dockerfile:build

#2.tag project
docker tag lisenmiao/topcom-auth lisenmiao/topcom-auth:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/topcom-auth:latest

#5.pull form docker hub
docker pull lisenmiao/topcom-auth

#6.run app
docker run -d -p 8800:8800 -t lisenmiao/topcom-auth --name topcom-auth