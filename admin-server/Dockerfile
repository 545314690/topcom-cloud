FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /root/app
ADD target/admin-server-0.0.1-RELEASE.jar admin-server.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /admin-server.jar" ]