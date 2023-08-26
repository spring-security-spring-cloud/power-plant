FROM amazoncorretto:11-alpine-jdk
COPY target/power-plant-0.0.1-SNAPSHOT.jar power-plant-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/power-plant-0.0.1-SNAPSHOT.jar"]