-- 1. Insertar oficinas iniciales de prueba
INSERT INTO oficinas (nombre, ubicacion, codigo_oficina) VALUES ('Delegación Galicia - Lugo', 'Plaza de la Xunta S/N, Lugo', 'OFI-LUG');
INSERT INTO oficinas (nombre, ubicacion, codigo_oficina) VALUES ('Sede Central Madrid', 'Paseo de la Castellana 45, Madrid', 'OFI-MDR');

-- 2. Insertar usuario Administrador
-- La contraseña es 'admin123' cifrada con BCrypt (requisito de SecurityConfig)
INSERT INTO usuarios (nombre_completo, username, password, rol, oficina_id) VALUES ('Administrador Ineco', 'admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.p.qGFlG4.K1X/yVbSGB7A3H6n1Y8WkW', 'ROLE_ADMIN', 2);

-- 3. Insertar usuario Consultor Técnico
-- La contraseña es 'user123' cifrada con BCrypt
INSERT INTO usuarios (nombre_completo, username, password, rol, oficina_id) VALUES ('Técnico de Área', 'tecnico', '$2a$10$8.gXmThWjWS0w0bWq0NqeuRxl.nKexD1TMBsO1H7m4r04.1.fQzOm', 'ROLE_CONSULTOR', 1);
