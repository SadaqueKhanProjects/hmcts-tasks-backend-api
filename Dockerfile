# syntax=docker/dockerfile:1

# ---- Build stage ----
FROM gradle:8.10.2-jdk21 AS build
WORKDIR /workspace
# copy the whole backend project (your build uses Gradle wrapper + plugins)
COPY . .
RUN chmod +x gradlew
# build the fat jar
RUN ./gradlew --no-daemon clean bootJar

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
# allow compose to pass the same port you keep in .env
ARG SERVER_PORT=4000
ENV SERVER_PORT=${SERVER_PORT}
EXPOSE ${SERVER_PORT}
# copy the jar built in the previous stage
COPY --from=build /workspace/build/libs/*.jar app.jar
# run Spring Boot on the requested port
ENTRYPOINT ["sh","-c","java -Dserver.port=${SERVER_PORT} -jar app.jar"]