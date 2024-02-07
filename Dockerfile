# Start with a base image containing linux and java
FROM eclipse-temurin:11-alpine
USER root

# Add Maintainer Info
LABEL maintainer="olgakil90@gmail.com"

# The application's jar file
ARG JAR_FILE=target/Players-1.0.0-jar-with-dependencies.jar

# Add the application's jar to the container
ADD ${JAR_FILE} players.jar

WORKDIR /app
EXPOSE 8080
RUN apk add --no-cache curl
HEALTHCHECK CMD curl http://localhost:8080/actuator/health
# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/players.jar"]