FROM openjdk
ADD target/rest-api-v1-react-0.0.1-SNAPSHOT.jar restApp.jar
ENTRYPOINT ["java","-jar","restApp.jar"]
