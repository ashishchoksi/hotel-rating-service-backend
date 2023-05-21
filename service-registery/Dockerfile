FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} service-registery.jar
ENTRYPOINT ["java", "-jar", "service-registery.jar"]