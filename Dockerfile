# Build stage
FROM maven:3.8.4-openjdk-18-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn package -DskipTests

# Package stage
FROM openjdk:18-jdk-slim
COPY --from=build /app/target/Start-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
