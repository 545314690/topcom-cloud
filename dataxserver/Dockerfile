FROM 192.168.1.12:5000/ubuntu-jdk-python2

ADD target/dataxserver-0.0.1-SNAPSHOT.jar dataxserver.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /dataxserver.jar" ]
EXPOSE 8001
