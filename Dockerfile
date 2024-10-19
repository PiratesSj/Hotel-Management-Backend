# Use an OpenJDK 8 base image
FROM openjdk:8-jdk-alpine
COPY . .
RUN mvn clean package -DskipTests

# Copy the JAR file from the target directory
COPY target/Hotel-Managment-0.0.1-SNAPSHOT.jar Hotel-Management.jar

# Expose the port your application will run on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "Hotel-Management.jar"]
