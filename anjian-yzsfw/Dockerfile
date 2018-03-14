FROM frolvlad/alpine-oraclejdk8:slim
ADD target/anjian-yzsfw-0.0.1-SNAPSHOT.jar anjian-yzsfw.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /anjian-yzsfw.jar" ]
EXPOSE 18124
