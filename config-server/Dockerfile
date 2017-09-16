FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /root/app
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /config-server.jar" ]
EXPOSE 7001
