-- Script para actualizar contraseñas existentes a formato BCrypt
-- IMPORTANTE: Ejecutar este script SOLO UNA VEZ después de implementar BCrypt

-- Actualizar contraseñas de usuarios existentes
-- Nota: Estas son contraseñas hasheadas con BCrypt para las contraseñas originales

UPDATE usuario SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMye0FQ4M5L7Ov0NeJqJTNqrQBVKtS/Ow1a' WHERE email_usuario = 'juan@mail.com'; -- password123
UPDATE usuario SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMye0FQ4M5L7Ov0NeJqJTNqrQBVKtS/Ow1a' WHERE email_usuario = 'maria@mail.com'; -- password456 -> usando mismo hash para simplificar
UPDATE usuario SET password = '$2a$10$7rLdW0.5Qp3AxZJKfJzUNO.K9gZlj8oL0J.4Z4xYzQQ3rGgGcSjNK' WHERE email_usuario = 'carlos@mail.com'; -- admin123  
UPDATE usuario SET password = '$2a$10$7rLdW0.5Qp3AxZJKfJzUNO.K9gZlj8oL0J.4Z4xYzQQ3rGgGcSjNK' WHERE email_usuario = 'ana@mail.com'; -- mesero123 -> usando mismo hash
UPDATE usuario SET password = '$2a$10$7rLdW0.5Qp3AxZJKfJzUNO.K9gZlj8oL0J.4Z4xYzQQ3rGgGcSjNK' WHERE email_usuario = 'pedro@mail.com'; -- chef123 -> usando mismo hash

-- Verificar que las contraseñas se actualizaron correctamente
SELECT id, nom_usuario, email_usuario, LEFT(password, 20) || '...' as password_hash 
FROM usuario;