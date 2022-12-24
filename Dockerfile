FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ims.jar
ENTRYPOINT ["java","-jar","/ims.jar"]