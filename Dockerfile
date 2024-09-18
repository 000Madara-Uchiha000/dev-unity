# Build stage
FROM maven:3.8.6-openjdk-20 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Runtime stage
FROM openjdk:20-slim
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
