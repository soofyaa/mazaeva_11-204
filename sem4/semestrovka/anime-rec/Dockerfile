# Используем образ Maven с JDK 17 для этапа сборки
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Сборка проекта, исключая тесты
RUN mvn clean package -DskipTests

# Используем образ OpenJDK для запуска приложения
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем скомпилированный JAR файл из стадии сборки
COPY --from=build /app/target/*.jar app.jar

# Открываем порт для приложения
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
