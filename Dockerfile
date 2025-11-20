# Dockerfile
FROM maven:3.9.4-eclipse-temurin-17-alpine

# 2. Set working directory
WORKDIR /app

# 3. Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# 4. Copy source code
COPY src ./src

# 5. Build the app
RUN mvn clean package -DskipTests

# 6. Expose port
EXPOSE 8080

# 7. Run the jar
CMD ["java", "-jar", "target/gestion-colis-0.0.1-SNAPSHOT.jar"]
