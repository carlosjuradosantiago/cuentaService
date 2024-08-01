FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/cuenta-service-0.0.1-SNAPSHOT.jar cuenta-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/cuenta-service.jar"]
