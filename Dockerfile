FROM openjdk:18-jdk

WORKDIR /app

COPY target/start.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]