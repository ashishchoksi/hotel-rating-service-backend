FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} hotel-service.jar
ENTRYPOINT ["java", "-jar", "hotel-service.jar"]