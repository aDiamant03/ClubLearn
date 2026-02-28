# ClubLearn - Dockerized Application

## 📋 О проекте

Этот проект представляет собой многоконтейнерное приложение, полностью управляемое через **Docker Compose**. Архитектура включает:

- **Backend приложение** (Kotlin/Java) - порт `8080`
- **PostgreSQL база данных** - порт `5432`

## 🚀 Быстрый старт

Надо ввести в **Terminal**
```
git clone <https://github.com/aDiamant03/ClubLearn/tree/main>
cd ClubLearn
docker-compose up --build
```

## 🛠 Технологии

- **Backend**: Kotlin, Spring Boot
- **База данных**: PostgreSQL 15
- **Контейнеризация**: Docker, Docker Compose
- **Сборка**: Gradle
