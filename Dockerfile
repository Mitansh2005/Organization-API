#Build stage:
FROM maven:3.8.4-openjdk-17-slim AS build

#set working dir
WORKDIR /app

#copy pom.xml
COPY pom.xml .

#copy src code
COPY src ./src

#build the application
RUN mvn clean package -DskipTests

# Runtime Stage: Use OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]