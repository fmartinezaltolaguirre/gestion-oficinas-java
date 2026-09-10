# Cómo contribuir

¡Gracias por contribuir! Sigue este flujo recomendado:

1. Abre un issue describiendo el bug o la mejora propuesta.
2. Crea una rama desde `main` con un nombre descriptivo:
   - `feature/<descripcion-corta>`
   - `fix/<descripcion-corta>`
3. Realiza cambios en la rama y añade tests cuando corresponda.
4. Haz commits atómicos y con mensajes claros en modo imperativo. Ejemplo:
   - "Añade endpoint para crear reservas"
   - "Corrige validación de fecha en ReservaValidator"
5. Abre un Pull Request comparando tu rama con `main`.
6. Describe en el PR qué cambia y cómo probarlo; agrega capturas o comandos si aplica.
7. Espera la revisión; responde a comentarios y actualiza el PR si sea necesario.

Estilo de código
- Java: sigue las convenciones estándar (nombres CamelCase, 4 espacios o tabulación según configuración del proyecto).
- Añade tests unitarios y/o de integración.

Integración continua
- Si el proyecto tiene GitHub Actions u otro CI, los tests y build se ejecutarán automáticamente.

Licencia y contribuciones
- Al contribuir, aceptas que tu contribución se publique bajo la licencia del proyecto (ver `LICENSE`).
