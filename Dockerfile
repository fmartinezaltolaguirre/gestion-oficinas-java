# Fase 1: Compilación del proyecto usando Maven y Java 25
 WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto saltándose los tests para acelerar el proceso en local
RUN mvn clean package -DskipTests

# Fase 2: Ejecución de la aplicación
 WORKDIR /app
# Copia el archivo .jar generado en la fase anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
