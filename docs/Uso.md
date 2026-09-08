# Uso / Endpoints

La API expone endpoints REST para gestionar oficinas, empleados, recursos y reservas. Se asume prefijo `/api`.

Ejemplos de endpoints

- Listar oficinas
  - GET /api/oficinas
  - curl:
    ```bash
    curl http://localhost:8080/api/oficinas
    ```

- Obtener oficina por id
  - GET /api/oficinas/{id}

- Crear oficina
  - POST /api/oficinas
  - Body JSON:
    ```json
    {
      "nombre": "Oficina Centro",
      "direccion": "Calle 1, 2"
    }
    ```
  - curl:
    ```bash
    curl -X POST http://localhost:8080/api/oficinas \
      -H "Content-Type: application/json" \
      -d '{"nombre":"Oficina Centro","direccion":"Calle 1"}'
    ```

- Listar empleados
  - GET /api/empleados

- Crear reserva
  - POST /api/reservas
  - Body JSON ejemplo:
    ```json
    {
      "recursoId": 1,
      "usuarioId": 2,
      "inicio": "2026-09-10T09:00:00",
      "fin": "2026-09-10T10:00:00"
    }
    ```
  - curl:
    ```bash
    curl -X POST http://localhost:8080/api/reservas \
      -H "Content-Type: application/json" \
      -d '{"recursoId":1,"usuarioId":2,"inicio":"2026-09-10T09:00:00","fin":"2026-09-10T10:00:00"}'
    ```

Autenticación
- Si la aplicación usa seguridad (JWT/OAuth2), añade pasos para obtener el token y ejemplos `Authorization: Bearer <token>`.

Errores comunes
- 400 Bad Request: payload inválido o formatos de fecha incorrectos.
- 401 Unauthorized: faltan credenciales o token inválido.
- 404 Not Found: recurso no encontrado.
- 409 Conflict: conflicto de reserva (solapamiento).

Notas
- Ajusta las rutas y payloads reales según el código y los controladores del proyecto.
