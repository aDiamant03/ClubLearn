FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app
COPY build/libs/app.jar app.jar


