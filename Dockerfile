# Use an official Maven image to build the spring boot app
FROM maven:latest AS build


# Set the working directory
WORKDIR /app

# Copy the pom.xml file and install the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build image to the runtime image
COPY --from=build /app/target/cybershield-0.0.1-SNAPSHOT.jar .

# Expose the port
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "cybershield-0.0.1-SNAPSHOT.jar"]
