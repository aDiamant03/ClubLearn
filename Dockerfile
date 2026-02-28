# Multistage сборка
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

# Собираем приложение (jar файл появится после этой команды)
RUN ./gradlew clean build -x test

# Второй этап: ФИНАЛЬНЫЙ ОБРАЗ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Создаем группы для разных ролей
RUN addgroup --system --gid 1001 students && \
    addgroup --system --gid 1002 teachers && \
    addgroup --system --gid 1003 admins

# Создаем одного пользователя, но добавляем его во все группы
RUN adduser --system --uid 1001 --gid 1001 appuser && \
    usermod -a -G students,teachers,admins appuser

# Копируем jar ИЗ ПЕРВОГО ЭТАПА (builder) ВО ВТОРОЙ ЭТАП
COPY --from=builder --chown=appuser:appuser /app/build/libs/*.jar app.jar

# Создаем директории с разными группами
RUN mkdir -p /data/student /data/teacher /data/admin && \
    chown appuser:students /data/student && \
    chown appuser:teachers /data/teacher && \
    chown appuser:admins /data/admin && \
    chmod 750 /data/student /data/teacher /data/admin

# Переключаемся на пользователя appuser
USER appuser

# Порт
EXPOSE 8080

# Точка входа в приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
#Это финальная точка, готовая к запуску