# Step 1: Build Stage
FROM maven:3.8.7-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the build container
COPY pom.xml ./
COPY src ./src

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Step 2: Runtime Stage
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on (adjust as needed)
EXPOSE 8082

# Set the default command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
