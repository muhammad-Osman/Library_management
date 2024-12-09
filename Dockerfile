FROM openjdk:17-jdk-slim
COPY target/library-system.jar library-system.jar
ENTRYPOINT ["java", "-jar", "/library-system.jar"]