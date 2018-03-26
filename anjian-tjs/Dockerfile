FROM frolvlad/alpine-oraclejdk8:slim
ADD target/anjian-tjs-0.0.1-SNAPSHOT.jar anjian-tjs.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /anjian-tjs.jar" ]
EXPOSE 18124
