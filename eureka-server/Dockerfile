# Используем минималистичный образ Java
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# УСТАНАВЛИВАЕМ CURL ДЛЯ HEALTHCHECK
RUN apt-get update && apt-get install -y curl

# Копируем скомпилированный JAR-файл в контейнер
COPY build/libs/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]