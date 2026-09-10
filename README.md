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
