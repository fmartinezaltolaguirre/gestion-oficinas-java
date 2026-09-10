-- Crear tablas si no existen (Estructura nativa)
CREATE TABLE IF NOT EXISTS oficinas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    ubicacion VARCHAR(150),
    codigo_oficina VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    oficina_id BIGINT REFERENCES oficinas(id)
);

-- Limpiar datos antiguos para evitar duplicados
TRUNCATE TABLE usuarios CASCADE;
TRUNCATE TABLE oficinas CASCADE;

-- 1. Insertar oficinas de Ineco
INSERT INTO oficinas (id, nombre, ubicacion, codigo_oficina) VALUES (1, 'Sede Central Madrid', 'Paseo de la Castellana 45, Madrid', 'OFI-MDR');
INSERT INTO oficinas (id, nombre, ubicacion, codigo_oficina) VALUES (2, 'Delegación Galicia - Lugo', 'Plaza de la Xunta S/N, Lugo', 'OFI-LUG');

-- 2. Insertar usuarios (Contraseña: admin123)
INSERT INTO usuarios (nombre_completo, username, password, rol, oficina_id) 
VALUES ('Administrador Ineco', 'admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.p.qGFlG4.K1X/yVbSGB7A3H6n1Y8WkW', 'ROLE_ADMIN', 1);

INSERT INTO usuarios (nombre_completo, username, password, rol, oficina_id) 
VALUES ('Técnico de Área', 'tecnico', '$2a$10$8.gXmThWjWS0w0bWq0NqeuRxl.nKexD1TMBsO1H7m4r04.1.fQzOm', 'ROLE_CONSULTOR', 2);
