# Stage 1: Build the application
FROM maven:3.8.6-openjdk-8 AS builder
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight OpenJDK 8 runtime for the final image
FROM openjdk:8-jdk-alpine
COPY --from=builder /app/target/your-app-name.jar /app/your-app-name.jar
ENTRYPOINT ["java", "-jar", "/app/your-app-name.jar"]
