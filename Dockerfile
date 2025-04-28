FROM openjdk:21-jdk
ADD target/challenge-backend.jar challenge-backend.jar
ENTRYPOINT ["java", "-jar", "/challenge-backend.jar"]