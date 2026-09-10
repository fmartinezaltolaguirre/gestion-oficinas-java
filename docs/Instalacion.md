# Instalación y ejecución

## Requisitos
- Java JDK 25+ instalado
- Maven 3.6+ (o Gradle si el proyecto lo usa)
- Git
- Base de datos: H2 (desarrollo) o PostgreSQL/MySQL (producción)

## Clonar el repositorio
```bash
git clone https://github.com/fmartinezaltolaguirre/gestion-oficinas-java.git
cd gestion-oficinas-java
git fetch origin add/docs-wiki-pages
git checkout add/docs-wiki-pages
```

## Ejecutar con Maven
Compilar y generar el JAR:
```bash
mvn clean package
```
Ejecutar el JAR:
```bash
java -jar target/gestion-oficinas-java-<version>.jar
```
Ejecutar en modo desarrollo:
```bash
mvn spring-boot:run
```

## Configuración
Ajustes en `application.properties` o `application.yml`:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `server.port` (por defecto 8080)

Para desarrollo rápido puedes usar H2 en memoria; para producción configura PostgreSQL o MySQL.

## Docker (opcional)
Dockerfile básico:
```dockerfile
FROM eclipse-temurin:25-jdk-jammy
COPY target/gestion-oficinas-java-<version>.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Compose ejemplo (postgres + app):
```yaml
version: '3.8'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: gestion
      POSTGRES_USER: user
      POSTGRES_PASSWORD: pass
    ports:
      - "5432:5432"
  app:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/gestion
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: pass
    depends_on:
      - db
    ports:
      - "8080:8080"
```

