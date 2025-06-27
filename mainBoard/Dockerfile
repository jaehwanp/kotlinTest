FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

## 1단계: 빌드 환경
#FROM gradle:8.5.0-jdk17-alpine AS build
#COPY --chown=gradle:gradle . /app
#WORKDIR /app
#RUN gradle bootJar --no-daemon
#
## 2단계: 실제 실행 환경
#FROM eclipse-temurin:17-jdk-alpine
#COPY --from=build /app/build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]
