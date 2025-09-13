# 🏃 Runtime image only (JAR already built outside Docker)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar from target/
COPY target/*.jar app.jar

# Expose service port (change for each service)
EXPOSE 8080

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]
