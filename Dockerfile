FROM gradle:8.3-jdk17 AS build
WORKDIR /app
COPY . .
CMD ["java", "-jar", "/app/build/libs/kamr-0.0.1.jar"]