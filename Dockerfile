# Используем легковесный образ с Java
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем jar в контейнер
COPY build/libs/team-project-0.0.1-SNAPSHOT.jar /app/app.jar

# Порт (если это веб-приложение - можно изменить)
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]