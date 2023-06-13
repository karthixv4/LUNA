FROM openjdk:18-jdk
VOLUME /tmp
COPY target/Start.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
