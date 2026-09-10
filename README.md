# Gestión de Oficinas (Java)

Aplicación web sencilla para administrar oficinas y usuarios, con backend en Java (Spring Boot) y frontend basado en plantillas HTML.

## Stack
- Lenguajes: Java (backend), HTML (plantillas / vistas)
- Framework / runtime: Spring Boot (Maven)
- Dependencias destacadas: Spring MVC, Thymeleaf, Spring Data JPA, Log4j2

## Descripción breve
Provee páginas para autenticación, listado y gestión de oficinas y usuarios. Sigue una arquitectura MVC clásica: controladores sirven vistas (templates), la capa de servicio contiene la lógica y los repositorios manejan la persistencia.

## Estructura principal
```
pom.xml                    # POM de Maven
src/
  main/
    java/com/ineco/
      config/               # Configuración de la aplicación
      controller/           # Controladores HTTP
      filter/               # Filtros (seguridad/sesión)
      model/                # Entidades / modelos de dominio
      repository/           # Repositorios (persistencia)
      service/              # Lógica de negocio
      util/                 # Utilidades
    resources/
      application.yml       # Configuración principal (DB, puertos, etc.)
      log4j2.xml            # Logging
      templates/            # Plantillas HTML (Thymeleaf)
        login.html
        oficinas/
          lista.html
          formulario.html
        usuarios/
src/main/webapp/           # Recursos web adicionales
scripts/                   # Scripts auxiliares
docs/                      # Documentación
.github/                   # Configuración de GitHub (workflows, templates)
```

## Cómo ejecutar
1. Clonar el repositorio:
```bash
git clone https://github.com/fmartinezaltolaguirre/gestion-oficinas-java.git
cd gestion-oficinas-java
```
2. Ajustar la configuración de la base de datos en `src/main/resources/application.yml`.
3. Ejecutar en modo desarrollo:
```bash
mvn spring-boot:run
```
  o empaquetar y ejecutar el JAR:
```bash
mvn clean package
java -jar target/*.jar
```
4. Abrir `http://localhost:8080` (o el puerto configurado).

Rutas observadas (según plantillas):
- `/login` → `templates/login.html`
- Rutas de oficinas → `templates/oficinas/lista.html`, `templates/oficinas/formulario.html`

## Configuración de ejemplo (application.yml)
Ajusta según tu BD:
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tu_db
    username: tu_usuario
    password: tu_contraseña
  jpa:
    hibernate:
      ddl-auto: update
```

## Logging
La configuración de logging se encuentra en `src/main/resources/log4j2.xml`.

## Tests
Ejecutar las pruebas con:
```bash
mvn test
```

## Contribuir
- Crea una rama `feature/` o `fix/`.
- Abre un Pull Request describiendo los cambios.
- Añade tests cuando sea posible.

## Notas
- No se encontró un archivo `LICENSE`; añade uno si el proyecto será público.
- Revisa `application.yml` antes de desplegar en producción (credenciales, propiedades de seguridad).
- La carpeta `scripts/` puede contener utilidades de despliegue — revísala antes de ejecutar.
