### BUILD image

# mvn clean package

FROM maven:3.6.3-jdk-14 as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]