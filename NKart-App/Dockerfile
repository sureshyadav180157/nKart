# Use a base image with Java runtime
FROM openjdk:17-jdk-slim
Label maintainer "nkart-app"
WORKDIR /app
COPY target/nKart-3.3.0-SNAPSHOT.jar nkart-app.jar
ENTRYPOINT ["java","-jar","nkart-app.jar"]