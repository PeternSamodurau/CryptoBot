# Используем базовый образ с Java 17
FROM eclipse-temurin:17-jdk-jammy

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный jar-файл из папки build/libs в контейнер
# и переименовываем его в app.jar для простоты
COPY build/libs/bot-commands-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
