# Stage 1: Build the application
FROM maven:3.8.6-openjdk-8 AS builder
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests
RUN ls -l /app/target/  # Add this line to check the target contents

# Stage 2: Use a lightweight OpenJDK 8 runtime for the final image
FROM openjdk:8-jdk-alpine
COPY --from=builder /app/target/Hotel-Management-0.0.1-SNAPSHOT.jar /app/hotel-management.jar
ENTRYPOINT ["java", "-jar", "/app/hotel-management.jar"]
