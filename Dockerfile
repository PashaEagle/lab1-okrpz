#1
FROM gradle:4.6-jdk8-alpine AS build

COPY . /home/source/java
WORKDIR /home/source/java

USER root
RUN chown -R gradle /home/source/java

USER gradle
RUN gradle clean build 

#2
FROM openjdk:8

WORKDIR /home/application/java

COPY --from=build "/home/source/java/build/libs/telegram-bot.jar" .
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/home/application/java/telegram-bot.jar", "--spring.profiles.active="]