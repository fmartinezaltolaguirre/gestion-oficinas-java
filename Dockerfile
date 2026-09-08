# Fase 1: Compilación del proyecto usando Maven y Java 17
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto saltándose los tests para acelerar el proceso en local
RUN mvn clean package -DskipTests

# Fase 2: Ejecución de la aplicación
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copia el archivo .jar generado en la fase anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
