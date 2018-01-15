FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /root/app
ADD target/zuul-api-0.0.1-SNAPSHOT.jar zuul-api.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /zuul-api.jar" ]
EXPOSE 5555
