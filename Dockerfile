FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

COPY ${JAR_FILE} docker_test.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/docker_test.jar"]