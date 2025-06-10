# Базовый образ с Java
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл
COPY build/libs/newsfeed.jar /app/newsfeed.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/newsfeed.jar"]
