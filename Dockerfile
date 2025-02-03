# Use an official OpenJDK runtime as the base image
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory inside the container to /app (or any other directory name you prefer)
WORKDIR /noteslist

# Copy the Maven build files
COPY pom.xml .
COPY src ./src

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn ./.mvn

# Build the application using Maven (without running tests)
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the final stage
FROM eclipse-temurin:17-jre-jammy

# Set the working directory inside the container (again, you can use /noteslist if desired)
WORKDIR /noteslist

# Copy the JAR file from the builder stage
# Change the filename to match your generated JAR file (use * if unsure of the full name)
COPY --from=builder /noteslist/target/Noteslist-*.jar noteslist.jar

# Expose the port your application runs on (default Spring Boot port is 8080)
EXPOSE 8080

# Set environment variables for production
ENV SPRING_PROFILES_ACTIVE=prod

# Command to run the application
ENTRYPOINT ["java", "-jar", "noteslist.jar"]
