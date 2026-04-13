# Первый этап: СБОРКА
FROM eclipse-temurin:17-jdk-jammy AS builder
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
# Собираем приложение
RUN ./gradlew clean build -x test

# Второй этап: ФИНАЛЬНЫЙ ОБРАЗ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
# Создаем обычного пользователя (без лишних групп)
RUN adduser --system --uid 1001 appuser
# Копируем jar ИЗ ПЕРВОГО ЭТАПА (builder) ВО ВТОРОЙ ЭТАП
COPY --from=builder /app/build/libs/*.jar app.jar
RUN mkdir -p /data
USER 1001
# Порт
EXPOSE 8080
# Точка входа в приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
