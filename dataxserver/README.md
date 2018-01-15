"# dataxserver" 
#1.build project 
./mvnw install -Dmaven.test.skip=true dockerfile:build

#2.tag project
docker tag lisenmiao/dataxserver lisenmiao/dataxserver:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/dataxserver:latest

#5.pull form docker hub
docker pull lisenmiao/dataxserver

#6.run app
docker run -d -p 8001:8001 -t lisenmiao/dataxserver --name dataxserver