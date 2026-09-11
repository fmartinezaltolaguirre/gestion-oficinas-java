<<<<<<< HEAD
# 🚄 Sistema de Gestión de Oficinas Técnicas e Inventario Catastral - Ineco

Este ecosistema web corporativo de alto rendimiento representa la modernización integral y migración de la antigua infraestructura de datos basada en **Microsoft Access** hacia una arquitectura empresarial distribuida y segura. El sistema está diseñado específicamente para la supervisión de delegaciones territoriales, control de personal, trazas ferroviarias, censo catastral de expropiaciones e hitos jurídicos.

## 🛠️ Stack Tecnológico Corporativo
* **Backend Core:** Java 17 / Spring Boot 3.2.4
* **Seguridad y Accesos:** Spring Security 6 (Cifrado BCrypt de credenciales)
* **Motor de Plantillas:** Thymeleaf 3 (Arquitectura fragmentada reutilizable)
* **Persistencia y Datos:** Spring Data JPA / Hibernate 
* **Base de Datos Local:** H2 Database (Inyección directa controlada en memoria RAM)
* **Gestor Documental Cloud:** Conector Oficial SDK Microsoft Graph (SharePoint Online / Microsoft 365)
* **Diseño e Interfaz:** CSS3 Autónomo Avanzado (Efecto Glassmorphism & Branding Ineco)

## 📦 Arquitectura del Modelo Relacional Migrado
El corazón de la base de datos se compone de 6 entidades perfectamente relacionadas y normalizadas mediante JPA:
1. **Oficina (`Oficina`):** Delegación territorial que tutela los proyectos (Sedes Madrid, Lugo, etc.).
2. **Usuario (`Usuario`):** Personal técnico adscrito a una sede con control de roles (`ADMIN` / `CONSULTOR`).
3. **Proyecto (`Proyecto`):** Expediente de infraestructura de obra pública (vías ferroviarias, carreteras, aeropuertos).
4. **Propietario (`Propietario`):** Censo de titulares legales y razones sociales afectados por las trazas.
5. **Finca (`Finca`):** Inventario de predios y parcelas afectadas (Polígono, Parcela, Superficie expropiada en m²).
6. **Acta (`Acta`):** Hito técnico-jurídico y control financiero del Justiprecio (`OneToOne` con Finca).
7. **Alerta (`Alerta`):** Calendario automatizado de control de plazos legales y vencimientos de alegaciones.

## 🚀 Capacidades Especiales de la Aplicación

### 🧠 1. Motor de Búsqueda Inteligente (Lenguaje Natural NLP)
El sistema integra un **Servicio Parser Semántico** en la barra de navegación global que traduce texto libre cotidiano introducido por los operarios (ej: *"fincas de más de 1000 metros en Lugo"*) en consultas dinámicas de base de datos a través de **JPA Criteria**. Segmenta automáticamente los paneles elásticos de resultados cruzados.

### 📄 2. Gestión Documental Avanzada (SharePoint Ready)
Los formularios de *Proyectos* y *Fincas* cuentan con controles de carga binaria multipart (`MultipartFile`). Permiten adjuntar planos PDF generales de obra y planos parcelarios de catastro, vinculándolos directamente a los registros e indexándolos en el sistema.

---

## 💻 Manual de Despliegue Local (Ubuntu WSL)

### 1. Prerrequisitos de la Máquina
Asegúrate de contar con el Kit de Desarrollo oficial de Java en tu terminal Linux:
```bash
sudo apt update && sudo apt install openjdk-17-jdk
```

### 2. Arranque del Servidor Nativo
Para blindar la compilación e ignorar configuraciones o dependencias de releases superiores del framework en la nube, ejecuta la instrucción de inicio inyectando los flags de entorno y release locales de forma obligatoria:
```bash
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 ./mvnw clean spring-boot:run -Dmaven.compiler.release=17 -Djava.version=17
```

### 🔓 Credenciales Maestras de Acceso al Panel
Una vez levantado el servidor web en el puerto local, accede desde el navegador de Windows a la URL corporativa:
👉 `http://localhost:8089/gestion-oficinas/login`

* **Usuario Corporativo:** `admin`
* **Contraseña de Red:** `admin123`
=======
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
>>>>>>> origin/main
