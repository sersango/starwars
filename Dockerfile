# Temporal container to build project using gradle
FROM gradle:6.7-jdk11 AS BUILD_IMAGE

# Copying and build project
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x check --no-daemon

# Final container when to run the application
FROM openjdk:11.0.8-jre-slim

# Set micronaut server port from input variable
# Port by default: 8080
ARG APP_PORT=8080
ENV MICRONAUT_SERVER_PORT=$APP_PORT

EXPOSE $APP_PORT

# Copying jar file built in the previous container
RUN mkdir /app
COPY --from=BUILD_IMAGE /home/gradle/src/build/libs/*-all.jar /app/micronaut-starwars-app.jar

# Run application
ENTRYPOINT ["java", "-jar", "/app/micronaut-starwars-app.jar"]