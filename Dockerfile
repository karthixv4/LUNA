#
# Build stage
#
FROM openjdk:18-jdk AS build
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:18-jdk-slim
COPY --from=build /target/Start-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]