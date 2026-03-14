FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x ./gradlew

COPY src src

CMD ["./gradlew", "bootRun", "--no-daemon"]