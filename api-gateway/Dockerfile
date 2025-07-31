FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
