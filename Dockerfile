# Stage 1: Build the application
FROM maven:3.8.6-openjdk-8 AS builder

# Set the working directory
WORKDIR /app

# Copy your Maven project's pom.xml and source code to the container
COPY pom.xml ./
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# List files in the target directory to check if the JAR was created
RUN ls -l /app/target/

# Stage 2: Use a lightweight OpenJDK 8 runtime for the final image
FROM openjdk:8-jdk-alpine

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/target/Hotel-Managment-0.0.1-SNAPSHOT.jar /app/hotel-management.jar

# Set the entry point for the final image
ENTRYPOINT ["java", "-jar", "/app/hotel-management.jar"]
