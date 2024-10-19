# Use an OpenJDK 8 base image
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target directory
COPY target/Hotel-Managment-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
