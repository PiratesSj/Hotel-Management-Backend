# Stage 1: Build the application with Maven and OpenJDK 8
FROM maven:3.8.5-openjdk-8 AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight OpenJDK 8 runtime for the final image
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/Hotel-Managment-0.0.1-SNAPSHOT.jar hotel-management.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "hotel-management.jar"]
