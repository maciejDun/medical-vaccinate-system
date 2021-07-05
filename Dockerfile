FROM openjdk:17-ea-14-jdk-alpine3.14
ADD target/medical-vaccinate-system-0.0.1-SNAPSHOT.jar .
EXPOSE  8080
CMD java -jar medical-vaccinate-system-0.0.1-SNAPSHOT.jar
