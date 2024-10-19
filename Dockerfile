# Use an official OpenJDK 8 image as a parent image
FROM openjdk:8-jdk-alpine

# Set the application's port
EXPOSE 8080

# Add the JAR file and name it
ARG JAR_FILE=target/Hotel-Managment-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

# Run the app using java -jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
