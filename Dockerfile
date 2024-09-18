From maven:3.8.5-openjdk-17 As build
Copy . .
Run maven clean package -DskipTests

From openjdk:17.0.1-jdk-slim
Copy --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
Expose 8080
Entrypoint ["java", "-jar", "demo.jar"]
