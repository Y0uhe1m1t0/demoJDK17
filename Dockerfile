FROM openjdk:17

ADD target/weather_microservice-0.0.1-SNAPSHOT.jar weather_microservice.jar

ENTRYPOINT ["java", "-jar", "weather_microservice.jar"]