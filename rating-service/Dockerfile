FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} rating-service.jar
ENTRYPOINT ["java", "-jar", "rating-service.jar"]