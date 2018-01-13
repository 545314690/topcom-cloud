FROM ubuntu:16.04

RUN echo 'deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted universe multiverse \
         deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted universe multiverse \
         deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted universe multiverse \
         deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse \
         deb-src http://mirrors.aliyun.com/ubuntu/ xenial main restricted universe multiverse \
         deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted universe multiverse \
         deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted universe multiverse \
         deb-src http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse' \
         > /etc/apt/sources.list
RUN apt-get update && apt-get install -y software-properties-common python-software-properties \
    && add-apt-repository ppa:webupd8team/java && apt-get update && apt-get install oracle-java8-installer -y
VOLUME /root/app
ADD target/dataxserver-0.0.1-SNAPSHOT.jar dataxserver.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /dataxserver.jar" ]
EXPOSE 8001
