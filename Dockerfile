# Multistage сборка
# Используем легковесный образ с Java
# Первый этап:
FROM eclipse-temurin:17-jdk-jammy AS builder
# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем все необходимые файлы для Gradle Wrapper
COPY gradlew gradlew
COPY gradle gradle

# Проверяем, что файлы скопировались и даем права
RUN ls -la && \
    if [ -f "gradlew" ]; then \
        echo "gradlew found, setting permissions"; \
        chmod +x gradlew; \
    else \
        echo "ERROR: gradlew not found!"; \
        exit 1; \
    fi

# Копируем остальные файлы сборки
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

#COPY build/libs/team-project-0.0.1-SNAPSHOT.jar /app/app.jar
# Это был собран промежуточный образ, в котором можно запустить тесты.
#RUN chmod +x gradlew
RUN ./gradlew clean build -x test
# Второй этап:
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
RUN addgroup --system --gid 1001 student && \
    adduser --system --uid 1001 --gid 1001 student

# Копируем jar из первого этапа
COPY --from=builder --chown=student:student /app/build/libs/*.jar app.jar
USER student
# # Порт
EXPOSE 8080
# Точка входа в приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
# Это финальная сборка, готовая к запуску
