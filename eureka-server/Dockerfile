FROM frolvlad/alpine-oraclejdk8:slim
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka-server.jar" ]
EXPOSE 1111
