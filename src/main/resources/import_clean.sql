-- Datos iniciales para el sistema de restaurante

-- Insertar restaurantes
INSERT INTO restaurante (nombre, direccion, telefono) VALUES ('El Buen Sabor', 'Calle 15 No 23-45, Tulua', '2235567890');
INSERT INTO restaurante (nombre, direccion, telefono) VALUES ('La Casa del Chef', 'Carrera 10 No 30-12, Tulua', '2236781234');
INSERT INTO restaurante (nombre, direccion, telefono) VALUES ('Restaurante Gourmet', 'Avenida 5 No 18-90, Tulua', '2237892345');

-- Insertar usuarios
INSERT INTO usuario (nom_usuario, email_usuario, rol_usuario, tel_usuario, password, est_usuario) VALUES ('Juan Perez', 'juan@mail.com', 'CLIENTE', '3001234567', 'password123', 'ACTIVO');
INSERT INTO usuario (nom_usuario, email_usuario, rol_usuario, tel_usuario, password, est_usuario) VALUES ('Maria Garcia', 'maria@mail.com', 'CLIENTE', '3002345678', 'password456', 'ACTIVO');
INSERT INTO usuario (nom_usuario, email_usuario, rol_usuario, tel_usuario, password, est_usuario) VALUES ('Carlos Lopez', 'carlos@mail.com', 'ADMIN', '3003456789', 'admin123', 'ACTIVO');
INSERT INTO usuario (nom_usuario, email_usuario, rol_usuario, tel_usuario, password, est_usuario) VALUES ('Ana Martinez', 'ana@mail.com', 'MESERO', '3004567890', 'mesero123', 'ACTIVO');
INSERT INTO usuario (nom_usuario, email_usuario, rol_usuario, tel_usuario, password, est_usuario) VALUES ('Pedro Rodriguez', 'pedro@mail.com', 'CHEF', '3005678901', 'chef123', 'ACTIVO');

-- Insertar mesas para restaurante 1
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (4, true, 'MESA001', 1);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (2, true, 'MESA002', 1);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (6, true, 'MESA003', 1);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (4, false, 'MESA004', 1);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (8, true, 'MESA005', 1);

-- Insertar mesas para restaurante 2
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (4, true, 'MESA101', 2);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (2, true, 'MESA102', 2);
INSERT INTO mesa (num_sillas, est_mesa, cod_mesa, restaurante_id) VALUES (6, true, 'MESA103', 2);

-- Insertar parqueaderos
INSERT INTO parqueadero (cod_parqueadero, est_parqueadero, restaurante_id) VALUES ('PARK001', true, 1);
INSERT INTO parqueadero (cod_parqueadero, est_parqueadero, restaurante_id) VALUES ('PARK002', true, 1);
INSERT INTO parqueadero (cod_parqueadero, est_parqueadero, restaurante_id) VALUES ('PARK003', false, 1);
INSERT INTO parqueadero (cod_parqueadero, est_parqueadero, restaurante_id) VALUES ('PARK101', true, 2);
INSERT INTO parqueadero (cod_parqueadero, est_parqueadero, restaurante_id) VALUES ('PARK102', true, 2);

-- Insertar categorias de menu
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Entradas', 'entradas.jpg', 1);
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Platos Principales', 'principales.jpg', 1);
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Postres', 'postres.jpg', 1);
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Bebidas', 'bebidas.jpg', 1);
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Carnes', 'carnes.jpg', 2);
INSERT INTO categoria_menu (nombre, img_cat_menu, restaurante_id) VALUES ('Pastas', 'pastas.jpg', 2);

-- Insertar items de menu
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Empanadas', 8000.0, 'Empanadas de carne y pollo', true, 'empanadas.jpg', 1);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Ceviche', 15000.0, 'Ceviche de camaron fresco', true, 'ceviche.jpg', 1);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Bandeja Paisa', 25000.0, 'Plato tradicional colombiano', true, 'bandeja.jpg', 2);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Pollo a la Plancha', 18000.0, 'Pollo con ensalada y papas', true, 'pollo.jpg', 2);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Tiramisu', 12000.0, 'Postre italiano tradicional', true, 'tiramisu.jpg', 3);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Jugo Natural', 5000.0, 'Jugos de frutas naturales', true, 'jugo.jpg', 4);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Churrasco', 35000.0, 'Carne a la parrilla premium', true, 'churrasco.jpg', 5);
INSERT INTO items_menu (nom_item, prec_item, desc_item, est_item, img_item_menu, categoria_menu_id) VALUES ('Lasana', 22000.0, 'Lasana de carne con queso', true, 'lasana.jpg', 6);

-- Insertar reservas
INSERT INTO reserva (mesa_id, usuario_id, est_reserva, fech_reserva) VALUES (1, 1, 'ACTIVA', '2025-09-22 19:00:00');
INSERT INTO reserva (mesa_id, usuario_id, est_reserva, fech_reserva) VALUES (2, 2, 'ACTIVA', '2025-09-22 20:00:00');
INSERT INTO reserva (mesa_id, usuario_id, est_reserva, fech_reserva) VALUES (3, 1, 'COMPLETADA', '2025-09-21 18:30:00');

-- Insertar pedidos
INSERT INTO pedido (reserva_id, usuario_id, est_pedido, pre_tot_pedido) VALUES (1, 1, 'PENDIENTE', 43000.0);
INSERT INTO pedido (reserva_id, usuario_id, est_pedido, pre_tot_pedido) VALUES (2, 2, 'EN_PREPARACION', 27000.0);
INSERT INTO pedido (reserva_id, usuario_id, est_pedido, pre_tot_pedido) VALUES (3, 1, 'COMPLETADO', 35000.0);

-- Insertar detalles de pedido
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (1, 1, 2, 8000.0, 16000.0);
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (1, 3, 1, 25000.0, 25000.0);
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (1, 6, 1, 5000.0, 5000.0);
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (2, 4, 1, 18000.0, 18000.0);
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (2, 5, 1, 12000.0, 12000.0);
INSERT INTO detalle_pedido (pedido_id, item_menu_id, cant_item, prec_unitario, subtotal) VALUES (3, 7, 1, 35000.0, 35000.0);

-- Insertar chatbot
INSERT INTO chatbot (restaurant_id, usuario_id, input, respuesta) VALUES (1, 1, 'Cual es el horario de atencion?', 'Atendemos de lunes a domingo de 11:00 AM a 10:00 PM');
INSERT INTO chatbot (restaurant_id, usuario_id, input, respuesta) VALUES (1, 2, 'Tienen platos vegetarianos?', 'Si, tenemos varias opciones vegetarianas en nuestro menu');
INSERT INTO chatbot (restaurant_id, usuario_id, input, respuesta) VALUES (2, 1, 'Hacen domicilios?', 'Si, hacemos domicilios en toda la ciudad con un tiempo de entrega de 30-45 minutos');

-- Insertar registro de cambios
INSERT INTO registro_cambios (usuario_id, tip_cambio, regla_afectada, fech_cambio) VALUES (3, 'ACTUALIZACION_PRECIO', 'Items de menu - Bandeja Paisa', '2025-09-21 10:30:00');
INSERT INTO registro_cambios (usuario_id, tip_cambio, regla_afectada, fech_cambio) VALUES (3, 'NUEVA_CATEGORIA', 'Categoria de menu - Bebidas', '2025-09-20 15:45:00');
INSERT INTO registro_cambios (usuario_id, tip_cambio, regla_afectada, fech_cambio) VALUES (4, 'CAMBIO_ESTADO_MESA', 'Mesa MESA004 - No disponible', '2025-09-21 16:20:00');

-- Insertar analista de eventos
INSERT INTO analista_eventos (restaurant_id, tip_evento) VALUES (1, 'RESERVA_REALIZADA');
INSERT INTO analista_eventos (restaurant_id, tip_evento) VALUES (1, 'PEDIDO_COMPLETADO');
INSERT INTO analista_eventos (restaurant_id, tip_evento) VALUES (1, 'MESA_LIBERADA');
INSERT INTO analista_eventos (restaurant_id, tip_evento) VALUES (2, 'NUEVA_RESERVA');
INSERT INTO analista_eventos (restaurant_id, tip_evento) VALUES (2, 'CANCELACION_PEDIDO');