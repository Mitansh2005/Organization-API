#base image
FROM openjdk:17-jdk-slim

#set the working directory
WORKDIR /app

#copy pom.xml
COPY pom.xml .

#get all the dependency
RUN mvn dependency:go-offline

#copy the entire source code
COPY src ./src

#generate the jar file skip tests
RUN mvn package -DskipTests

#copy the jar file into the container
COPY target/*.jar app.jar

#expose the application port
EXPOSE 8080

#command to run the application
ENTRYPOINT ["java","-jar","app.jar"]