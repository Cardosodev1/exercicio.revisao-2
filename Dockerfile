FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src src


RUN mvn -DskipTests clean package

# Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ENV JAVA_OPTS="" \
    PORT=8081 \
    SPRING_PROFILES_ACTIVE=default

# Copia o jar do build
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT} -jar /app/app.jar"]