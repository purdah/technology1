# Use a lightweight OpenJDK image as the base image that will get the latest minor release every build
FROM eclipse-temurin:21-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the host machine to the container
COPY build/libs/techtest-*-SNAPSHOT.jar app.jar

# Expose the application port (default for Spring Boot is 8080, it should match the application.properties value)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]