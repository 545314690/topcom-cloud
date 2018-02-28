FROM frolvlad/alpine-oraclejdk8:slim
ADD target/topcom-bi-0.0.1-SNAPSHOT.jar topcom-bi.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /topcom-bi.jar" ]
EXPOSE 18123
