FROM openjdk:18-jdk
VOLUME /tmp
COPY target/Start-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
