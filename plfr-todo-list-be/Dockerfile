# Backend Dockerfile

# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the backend jar file into the container
COPY target/plfr-todo-list-be-0.0.1-SNAPSHOT.jar /app/backend.jar

# Expose the port the backend will run on
EXPOSE 8080

# Run the backend application
CMD ["java", "-jar", "backend.jar"]