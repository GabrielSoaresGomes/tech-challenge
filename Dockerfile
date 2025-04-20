FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/target/challenge-01-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 2004

ENTRYPOINT ["java", "-jar", "app.jar"]
