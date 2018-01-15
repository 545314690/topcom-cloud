"# zuul-api" 
#1.build project 
./mvnw install -Dmaven.test.skip=true dockerfile:build

#2.tag project
docker tag lisenmiao/zuul-api lisenmiao/zuul-api:latest

#3.login docker hub
docker login<br>
->input username ＆ password
#4.push to docker hub
docker push lisenmiao/zuul-api:latest

#5.pull form docker hub
docker pull lisenmiao/zuul-api

#6.run app
docker run -d -p 5555:5555 -t lisenmiao/zuul-api --name zuul-api