# Используем базовый образ с Java
FROM openjdk:17-jdk-slim

# Создаём рабочую папку внутри контейнера
WORKDIR /app

# Копируем JAR в контейнер
COPY target/UserServicePostgreSQL-1.0-SNAPSHOT.jar app.jar

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
