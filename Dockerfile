# Multistage сборка
# Используем легковесный образ с Java
# Первый этап:
FROM eclipse-temurin:17-jdk-jammy AS builder
# Рабочая директория внутри контейнера
WORKDIR /app
COPY build/libs/team-project-0.0.1-SNAPSHOT.jar /app/app.jar
# Это был собран промежуточный образ, в котором можно запустить тесты.


# Второй этап:
FROM eclipse-termium:17-jdk-jammy
WORKDIR /app
# Копируем jar из предудщей стадии
COPY --builder /app/app.jar .
# Порт
EXPOSE 8080
# Точка входа в приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
# Это финальная сборка, готовая к запуску
