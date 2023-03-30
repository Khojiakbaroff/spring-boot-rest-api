FROM openjdk:17
ADD target/rest-api-v1-react-0.0.1-SNAPSHOT.jar rest-app.jar
ENTRYPOINT ["java","-jar","rest-app.jar"]
EXPOSE 8080
