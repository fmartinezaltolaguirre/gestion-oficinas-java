
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
