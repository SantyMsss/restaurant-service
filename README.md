# Restaurant Service

Servicio backend REST para la gestión de restaurantes y sus mesas. Construido con Spring Boot 3, Java 17 y PostgreSQL.

## Tecnologías principales
- Java 17
- Spring Boot 3 (spring-boot-starter-web, spring-boot-starter-data-jpa)
- PostgreSQL (Driver JDBC)
- JPA / Hibernate
- Lombok (opcional, actualmente no se usan anotaciones en las entidades mostradas)

## Estructura de paquetes
```
co.edu.uceva.restaurantservice
 ├─ controller          -> Controladores REST (exponen endpoints HTTP)
 ├─ model
 │   ├─ dao             -> Interfaces DAO (Spring Data JPA Repositories)
 │   ├─ entities        -> Entidades JPA que representan las tablas
 │   └─ service         -> Servicios (interfaces y clases @Service con lógica de negocio)
 └─ RestaurantServiceApplication.java  -> Clase principal (punto de entrada)
```

## Arquitectura
Arquitectura en capas sencilla:
1. Controller: recibe las peticiones HTTP y delega en la capa de servicio.
2. Service: contiene la lógica de negocio y orquesta llamadas a los repositorios.
3. DAO (Repository): acceso a datos mediante Spring Data JPA.
4. Entities: mapeo objeto-relacional con anotaciones JPA.

Cross-Origin: Se permite acceso desde cualquier origen (`@CrossOrigin(origins="*")`) en los controladores.

## Versionado de API
Los controladores usan prefijos con versión `v1` y un sufijo por dominio de negocio:
- `/api/v1/restaurante-service`
- `/api/v1/mesa-service`
- `/api/v1/reserva-service`
- `/api/v1/pedido-service`
- `/api/v1/detalle-pedido-service`
- `/api/v1/usuario-service`
- `/api/v1/categoria-menu-service`
- `/api/v1/items-menu-service`
- `/api/v1/parqueadero-service`
- `/api/v1/chatbot-service`
- `/api/v1/analista-eventos-service`
- `/api/v1/registro-cambios-service`

## Endpoints por recurso

> Notas rápidas
> - Todas las rutas comparten el prefijo de versión indicado en cada encabezado.
> - Los métodos `POST`/`PUT` esperan un cuerpo JSON con la estructura de la entidad correspondiente (incluye `id` cuando se actualiza).
> - Cuando se indican fechas, deben enviarse en formato ISO 8601 (`yyyy-MM-dd'T'HH:mm:ss`).

### Restaurantes (`/api/v1/restaurante-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/restaurantes` | - | - | Lista todos los restaurantes. |
| GET | `/restaurantes/{id}` | - | - | Obtiene un restaurante por su identificador. |
| POST | `/restaurante` | - | JSON Restaurante | Crea un nuevo restaurante. |
| PUT | `/restaurante` | - | JSON Restaurante (con `id`) | Actualiza un restaurante existente. |
| DELETE | `/restaurantes/{id}` | - | - | Elimina un restaurante por ID. |
| GET | `/restaurantes/nombre/{nombre}` | - | - | Busca un restaurante por nombre exacto. |
| GET | `/restaurantes/direccion/{direccion}` | - | - | Busca restaurantes cuyo texto de dirección coincide. |

### Mesas (`/api/v1/mesa-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/mesas` | - | - | Lista todas las mesas. |
| GET | `/mesas/{id}` | - | - | Obtiene una mesa por ID. |
| POST | `/mesa` | - | JSON Mesa | Crea una nueva mesa. |
| PUT | `/mesa` | - | JSON Mesa (con `id`) | Actualiza una mesa existente. |
| DELETE | `/mesas/{id}` | - | - | Elimina una mesa por ID. |
| GET | `/mesas/restaurante/{restauranteId}` | - | - | Mesas asociadas a un restaurante. |
| GET | `/mesas/disponibles/restaurante/{restauranteId}` | - | - | Mesas disponibles de un restaurante. |
| GET | `/mesas/disponibles/capacidad/{numSillas}` | - | - | Mesas disponibles con capacidad mínima dada. |
| GET | `/mesas/codigo/{codMesa}` | - | - | Busca una mesa por su código. |

### Reservas (`/api/v1/reserva-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/reservas` | - | - | Lista todas las reservas. |
| GET | `/reservas/{id}` | - | - | Obtiene una reserva por ID. |
| POST | `/reserva` | - | JSON Reserva | Crea una nueva reserva. |
| PUT | `/reserva` | - | JSON Reserva (con `id`) | Actualiza una reserva existente. |
| DELETE | `/reservas/{id}` | - | - | Elimina una reserva. |
| GET | `/reservas/usuario/{usuarioId}` | - | - | Reservas asociadas a un usuario. |
| GET | `/reservas/mesa/{mesaId}` | - | - | Reservas realizadas sobre una mesa específica. |
| GET | `/reservas/estado/{estado}` | - | - | Reservas filtradas por estado. |
| GET | `/reservas/mesa/{mesaId}/activas` | - | - | Reservas activas de una mesa. |
| GET | `/reservas/rango` | `inicio`, `fin` | - | Reservas dentro de un rango de fechas. |
| GET | `/reservas/usuario/{usuarioId}/fecha` | `fecha` | - | Reservas de un usuario en una fecha puntual. |

### Pedidos (`/api/v1/pedido-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/pedidos` | - | - | Lista todos los pedidos. |
| GET | `/pedidos/{id}` | - | - | Obtiene un pedido por ID. |
| POST | `/pedido` | - | JSON Pedido | Crea un nuevo pedido. |
| PUT | `/pedido` | - | JSON Pedido (con `id`) | Actualiza un pedido existente. |
| DELETE | `/pedidos/{id}` | - | - | Elimina un pedido por ID. |
| GET | `/pedidos/usuario/{usuarioId}` | - | - | Pedidos asociados a un usuario. |
| GET | `/pedidos/estado/{estado}` | - | - | Pedidos filtrados por estado. |
| GET | `/pedidos/reserva/{reservaId}` | - | - | Pedidos vinculados a una reserva. |
| GET | `/pedidos/pendientes` | - | - | Pedidos cuyo estado es pendiente. |
| GET | `/pedidos/precio` | `min`, `max` | - | Pedidos dentro de un rango de precio total. |
| GET | `/pedidos/ventas/usuario/{usuarioId}` | - | - | Total de ventas acumuladas por usuario. |

### Detalle de pedido (`/api/v1/detalle-pedido-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/detalles` | - | - | Lista todos los detalles de pedido. |
| GET | `/detalles/{id}` | - | - | Obtiene un detalle por ID. |
| POST | `/detalle` | - | JSON DetallePedido | Crea un nuevo detalle de pedido. |
| PUT | `/detalle` | - | JSON DetallePedido (con `id`) | Actualiza un detalle existente. |
| DELETE | `/detalles/{id}` | - | - | Elimina un detalle por ID. |
| GET | `/detalles/pedido/{pedidoId}` | - | - | Detalles pertenecientes a un pedido. |
| GET | `/detalles/item/{itemMenuId}` | - | - | Detalles que incluyen un ítem del menú específico. |
| GET | `/detalles/pedido/{pedidoId}/subtotal` | - | - | Subtotal calculado para un pedido. |
| GET | `/detalles/mas-vendidos` | - | - | Ranking de ítems más vendidos (lista de arreglos con estadísticas). |
| GET | `/detalles/cantidad/mayor` | `cant` | - | Detalles con cantidad mayor al valor indicado. |

### Usuarios (`/api/v1/usuario-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/usuarios` (alias `/usuario`) | - | - | Lista todos los usuarios. |
| GET | `/usuarios/{id}` | - | - | Obtiene un usuario por ID. |
| POST | `/usuario` | - | JSON Usuario | Crea un nuevo usuario. |
| PUT | `/usuario` | - | JSON Usuario (con `id`) | Actualiza un usuario existente. |
| DELETE | `/usuarios/{id}` | - | - | Elimina un usuario. |
| GET | `/usuarios/email` | `email` | - | Busca un usuario por correo electrónico. |
| POST | `/login` | `email`, `password` | - | Login básico (retorna el usuario o 401). |
| GET | `/usuarios/rol/{rol}` | - | - | Usuarios filtrados por rol. |
| GET | `/usuarios/activos` | - | - | Usuarios con estado activo. |
| GET | `/usuarios/buscar` | `q` | - | Búsqueda por coincidencia en nombre. |
| GET | `/usuarios/existe` | `email` | - | Indica si existe un usuario con ese correo. |

### Categorías de menú (`/api/v1/categoria-menu-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/categorias` | - | - | Lista todas las categorías. |
| GET | `/categorias/{id}` | - | - | Obtiene una categoría por ID. |
| POST | `/categoria` | - | JSON CategoriaMenu | Crea una nueva categoría. |
| PUT | `/categoria` | - | JSON CategoriaMenu (con `id`) | Actualiza una categoría existente. |
| DELETE | `/categorias/{id}` | - | - | Elimina una categoría. |
| GET | `/categorias/restaurante/{restauranteId}` | - | - | Categorías asociadas a un restaurante. |
| GET | `/categorias/nombre/{nombre}` | - | - | Obtiene una categoría por nombre exacto. |
| GET | `/categorias/buscar` | `q` | - | Busca categorías por coincidencia parcial. |

### Ítems de menú (`/api/v1/items-menu-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/items` | - | - | Lista todos los ítems del menú. |
| GET | `/items/{id}` | - | - | Obtiene un ítem por ID. |
| POST | `/item` | - | JSON ItemsMenu | Crea un nuevo ítem. |
| PUT | `/item` | - | JSON ItemsMenu (con `id`) | Actualiza un ítem existente. |
| DELETE | `/items/{id}` | - | - | Elimina un ítem por ID. |
| GET | `/items/categoria/{categoriaId}` | - | - | Ítems asociados a una categoría. |
| GET | `/items/categoria/{categoriaId}/disponibles` | - | - | Ítems disponibles dentro de una categoría. |
| GET | `/items/buscar` | `q` | - | Busca ítems por coincidencia en el nombre. |
| GET | `/items/precio` | `min`, `max` | - | Ítems dentro de un rango de precios. |
| GET | `/items/disponibles` | - | - | Todos los ítems marcados como disponibles. |
| GET | `/items/baratos` | `max` | - | Ítems cuyo precio es menor o igual al valor dado. |

### Parqueaderos (`/api/v1/parqueadero-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/parqueaderos` | - | - | Lista todos los parqueaderos. |
| GET | `/parqueaderos/{id}` | - | - | Obtiene un parqueadero por ID. |
| POST | `/parqueadero` | - | JSON Parqueadero | Crea un nuevo parqueadero. |
| PUT | `/parqueadero` | - | JSON Parqueadero (con `id`) | Actualiza un parqueadero existente. |
| DELETE | `/parqueaderos/{id}` | - | - | Elimina un parqueadero. |
| GET | `/parqueaderos/restaurante/{restauranteId}` | - | - | Parqueaderos asociados a un restaurante. |

### Chatbot (`/api/v1/chatbot-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/chats` | - | - | Lista todas las interacciones del chatbot. |
| GET | `/chats/{id}` | - | - | Obtiene una interacción por ID. |
| POST | `/chat` | - | JSON Chatbot | Registra una nueva interacción. |
| PUT | `/chat` | - | JSON Chatbot (con `id`) | Actualiza una interacción existente. |
| DELETE | `/chats/{id}` | - | - | Elimina una interacción. |
| GET | `/chats/restaurante/{restaurantId}` | - | - | Interacciones filtradas por restaurante. |
| GET | `/chats/usuario/{usuarioId}` | - | - | Interacciones filtradas por usuario. |

### Analista de eventos (`/api/v1/analista-eventos-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/analistas` | - | - | Lista registros del analista de eventos. |
| GET | `/analistas/{id}` | - | - | Obtiene un registro por ID. |
| POST | `/analista` | - | JSON AnalistaEventos | Crea un nuevo registro. |
| PUT | `/analista` | - | JSON AnalistaEventos (con `id`) | Actualiza un registro existente. |
| DELETE | `/analistas/{id}` | - | - | Elimina un registro por ID. |
| GET | `/analistas/restaurante/{restaurantId}` | - | - | Registros filtrados por restaurante. |

### Registro de cambios (`/api/v1/registro-cambios-service`)

| Método | Path | Query params | Body | Descripción |
|--------|------|--------------|------|-------------|
| GET | `/registros` | - | - | Lista todos los registros de cambios. |
| GET | `/registros/{id}` | - | - | Obtiene un registro por ID. |
| POST | `/registro` | - | JSON RegistroCambios | Crea un nuevo registro. |
| PUT | `/registro` | - | JSON RegistroCambios (con `id`) | Actualiza un registro existente. |
| DELETE | `/registros/{id}` | - | - | Elimina un registro. |
| GET | `/registros/usuario/{usuarioId}` | - | - | Registros asociados a un usuario. |
| GET | `/registros/tipo/{tipo}` | - | - | Registros filtrados por tipo de cambio. |
| GET | `/registros/rango` | `inicio`, `fin` | - | Registros dentro de un rango de fechas. |

## Códigos de estado
- 200 OK: Operación exitosa.
- 404 Not Found: Recurso no encontrado.
- 400 Bad Request: Datos inválidos o campos requeridos faltantes.
- 401 Unauthorized: Credenciales inválidas (aplica al endpoint de login).
- 500 Internal Server Error: Error interno del servidor.

## 🧪 Ejemplos de pruebas para Postman

A continuación se presentan ejemplos completos para probar los endpoints principales usando Postman o cualquier cliente HTTP.

### 👤 Usuarios

#### ✅ Crear Usuario
```http
POST http://localhost:8080/api/v1/usuario-service/usuario
Content-Type: application/json

{
    "nomUsuario": "Juan Pérez",
    "emailUsuario": "juan.perez@example.com",
    "rolUsuario": "CLIENTE",
    "telUsuario": "3001234567",
    "password": "miPassword123",
    "estUsuario": "ACTIVO"
}
```

#### 🔄 Actualizar Usuario
```http
PUT http://localhost:8080/api/v1/usuario-service/usuario
Content-Type: application/json

{
    "id": 1,
    "nomUsuario": "Juan Pérez Actualizado",
    "emailUsuario": "juan.nuevo@example.com",
    "rolUsuario": "ADMIN",
    "telUsuario": "3009876543",
    "password": "nuevoPassword456",
    "estUsuario": "ACTIVO"
}
```

#### 📖 Listar Usuarios
```http
GET http://localhost:8080/api/v1/usuario-service/usuarios
```

#### 🔍 Buscar Usuario por Email
```http
GET http://localhost:8080/api/v1/usuario-service/usuarios/email?email=juan@mail.com
```

#### 🔐 Login
```http
POST http://localhost:8080/api/v1/usuario-service/login?email=juan@mail.com&password=password123
```

### 🏪 Restaurantes

#### ✅ Crear Restaurante
```http
POST http://localhost:8080/api/v1/restaurante-service/restaurante
Content-Type: application/json

{
    "nombre": "Restaurante Nuevo",
    "direccion": "Calle 123 No 45-67",
    "telefono": "2234567890"
}
```

#### 📖 Listar Restaurantes
```http
GET http://localhost:8080/api/v1/restaurante-service/restaurantes
```

#### 🔍 Obtener Restaurante por ID
```http
GET http://localhost:8080/api/v1/restaurante-service/restaurantes/1
```

### 🪑 Mesas

#### ✅ Crear Mesa
```http
POST http://localhost:8080/api/v1/mesa-service/mesa
Content-Type: application/json

{
    "numSillas": 4,
    "estMesa": true,
    "codMesa": "MESA999",
    "restaurante": {
        "id": 1
    }
}
```

#### 🔄 Actualizar Mesa
```http
PUT http://localhost:8080/api/v1/mesa-service/mesa
Content-Type: application/json

{
    "id": 1,
    "numSillas": 6,
    "estMesa": false,
    "codMesa": "MESA001",
    "restaurante": {
        "id": 1
    }
}
```

#### 📖 Listar Mesas
```http
GET http://localhost:8080/api/v1/mesa-service/mesas
```

#### 🔍 Mesas por Restaurante
```http
GET http://localhost:8080/api/v1/mesa-service/mesas/restaurante/1
```

#### ✅ Mesas Disponibles
```http
GET http://localhost:8080/api/v1/mesa-service/mesas/disponibles/restaurante/1
```

### 📅 Reservas

#### ✅ Crear Reserva
```http
POST http://localhost:8080/api/v1/reserva-service/reserva
Content-Type: application/json

{
    "mesa": {
        "id": 1
    },
    "usuario": {
        "id": 1
    },
    "fechReserva": "2025-12-25T19:00:00",
    "estReserva": "ACTIVA"
}
```

#### 📖 Listar Reservas
```http
GET http://localhost:8080/api/v1/reserva-service/reservas
```

#### 🔍 Reservas por Usuario
```http
GET http://localhost:8080/api/v1/reserva-service/reservas/usuario/1
```

### 🍽️ Categorías de Menú

#### ✅ Crear Categoría
```http
POST http://localhost:8080/api/v1/categoria-menu-service/categoria
Content-Type: application/json

{
    "nombre": "Postres",
    "imgCatMenu": "postres.jpg",
    "restaurante": {
        "id": 1
    }
}
```

#### 📖 Categorías por Restaurante
```http
GET http://localhost:8080/api/v1/categoria-menu-service/categorias/restaurante/1
```

### 🍕 Items de Menú

#### ✅ Crear Item
```http
POST http://localhost:8080/api/v1/items-menu-service/item
Content-Type: application/json

{
    "nomItem": "Pizza Margherita",
    "precItem": 15000.0,
    "descItem": "Pizza tradicional con tomate y mozzarella",
    "estItem": true,
    "imgItemMenu": "pizza.jpg",
    "categoriaMenu": {
        "id": 1
    }
}
```

#### 📖 Items por Categoría
```http
GET http://localhost:8080/api/v1/items-menu-service/items/categoria/1
```

#### 💰 Items por Rango de Precio
```http
GET http://localhost:8080/api/v1/items-menu-service/items/precio?min=10000&max=20000
```

### 🛍️ Pedidos

#### ✅ Crear Pedido
```http
POST http://localhost:8080/api/v1/pedido-service/pedido
Content-Type: application/json

{
    "usuario": {
        "id": 1
    },
    "reserva": {
        "id": 1
    },
    "estPedido": "PENDIENTE",
    "preTotPedido": 25000.0
}
```

#### 📖 Pedidos por Usuario
```http
GET http://localhost:8080/api/v1/pedido-service/pedidos/usuario/1
```

#### 🔍 Pedidos Pendientes
```http
GET http://localhost:8080/api/v1/pedido-service/pedidos/pendientes
```

### 🧾 Detalle de Pedido

#### ✅ Crear Detalle
```http
POST http://localhost:8080/api/v1/detalle-pedido-service/detalle
Content-Type: application/json

{
    "pedido": {
        "id": 1
    },
    "itemMenu": {
        "id": 1
    },
    "cantItem": 2,
    "precUnitario": 8000.0,
    "subtotal": 16000.0
}
```

#### 📖 Detalles por Pedido
```http
GET http://localhost:8080/api/v1/detalle-pedido-service/detalles/pedido/1
```

### 🚗 Parqueaderos

#### ✅ Crear Parqueadero
```http
POST http://localhost:8080/api/v1/parqueadero-service/parqueadero
Content-Type: application/json

{
    "codParqueadero": "PARK999",
    "estParqueadero": true,
    "restaurante": {
        "id": 1
    }
}
```

#### 📖 Parqueaderos por Restaurante
```http
GET http://localhost:8080/api/v1/parqueadero-service/parqueaderos/restaurante/1
```

### 🤖 Chatbot

#### ✅ Crear Interacción
```http
POST http://localhost:8080/api/v1/chatbot-service/chat
Content-Type: application/json

{
    "restaurantId": 1,
    "usuario": {
        "id": 1
    },
    "input": "¿Cuál es el horario de atención?",
    "respuesta": "Atendemos de lunes a domingo de 11:00 AM a 10:00 PM"
}
```

### ⚠️ Casos de Error para Validar

#### ❌ Usuario sin password (Error 400)
```http
POST http://localhost:8080/api/v1/usuario-service/usuario
Content-Type: application/json

{
    "nomUsuario": "Test Sin Password",
    "emailUsuario": "sinpass@example.com",
    "rolUsuario": "CLIENTE",
    "estUsuario": "ACTIVO"
}
```

#### ❌ Mesa con restaurante inexistente (Error 400)
```http
POST http://localhost:8080/api/v1/mesa-service/mesa
Content-Type: application/json

{
    "numSillas": 4,
    "estMesa": true,
    "codMesa": "MESA888",
    "restaurante": {
        "id": 999
    }
}
```

#### ❌ Login con credenciales incorrectas (Error 401)
```http
POST http://localhost:8080/api/v1/usuario-service/login?email=noexiste@mail.com&password=wrongpassword
```

### 📝 Notas Importantes

1. **Content-Type**: Siempre incluir `Content-Type: application/json` en peticiones POST/PUT
2. **IDs**: Los IDs se generan automáticamente, no los incluyas en peticiones POST
3. **Relaciones**: Para crear entidades relacionadas, solo envía el ID del objeto relacionado
4. **Fechas**: Usar formato ISO 8601: `yyyy-MM-ddTHH:mm:ss`
5. **Estados**: Los estados deben coincidir con los valores definidos en las entidades
6. **Validaciones**: La API valida campos requeridos y retorna errores descriptivos

## Variables de entorno / Configuración
Archivo `application.properties` (ejemplo esperado para PostgreSQL):
```
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant
spring.datasource.username=postgres
spring.datasource.password=0809
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## 🔐 Seguridad con BCrypt

### Implementación de BCrypt para contraseñas

El sistema implementa BCrypt para el hash seguro de contraseñas de usuarios, proporcionando seguridad de nivel producción.

### ✅ Componentes implementados:

#### 1. **Dependencia agregada**
- `spring-security-crypto` en [`pom.xml`](pom.xml)

#### 2. **Configuración creada**
- `PasswordConfig.java` con bean de `BCryptPasswordEncoder`

#### 3. **Service modificado**
- [`UsuarioServiceImpl.java`](src/main/java/co/edu/uceva/restaurantservice/model/service/UsuarioServiceImpl.java) actualizado con:
  - **Inyección de `PasswordEncoder`**
  - **Método `save()`** con encriptación automática de contraseñas
  - **Prevención de duplicados** por email
  - **Método `login()`** con verificación BCrypt usando `passwordEncoder.matches()`
  - **Método helper `isPasswordEncrypted()`** para detectar si ya está hasheada

#### 4. **Script SQL creado**
- `update_passwords_bcrypt.sql` para migrar contraseñas existentes

### 🛡️ Características de seguridad implementadas:

- **✅ Encriptación automática**: Todas las contraseñas nuevas se encriptan con BCrypt
- **✅ Verificación segura**: El login usa `passwordEncoder.matches()` en lugar de comparación directa
- **✅ Prevención de duplicados**: No permite crear usuarios con emails existentes
- **✅ Detección inteligente**: Solo encripta contraseñas que no están ya hasheadas

### 🧪 Cómo probar la funcionalidad:

#### Crear nuevo usuario (contraseña se encripta automáticamente):
```http
POST http://localhost:8080/api/v1/usuario-service/usuario
Content-Type: application/json

{
    "nomUsuario": "Usuario Test",
    "emailUsuario": "test@example.com",
    "rolUsuario": "CLIENTE",
    "telUsuario": "3001234567",
    "password": "miPasswordTextoPlano",
    "estUsuario": "ACTIVO"
}
```

#### Login con contraseña encriptada:
```http
POST http://localhost:8080/api/v1/usuario-service/login?email=test@example.com&password=miPasswordTextoPlano
```

### 📋 Verificaciones de estado:

- ✅ **Compilación exitosa**
- ✅ **Inicio de aplicación sin errores**
- ✅ **Base de datos inicializada correctamente**
- ✅ **Contraseñas nunca se almacenan en texto plano**

### ⚠️ Importante:

1. **Las contraseñas se hashean automáticamente** al crear o actualizar usuarios
2. **El login verifica de forma segura** usando BCrypt
3. **No es posible recuperar contraseñas en texto plano** desde la base de datos
4. **Los hashes BCrypt son únicos** incluso para la misma contraseña

**¡Tu API ahora tiene seguridad de contraseñas nivel producción con BCrypt!** 🔐

## Ejecución local
1. Asegúrate de tener PostgreSQL en ejecución y la base de datos creada.
2. Configura las credenciales en `application.properties`.
3. Compila y ejecuta:
```bash
mvn spring-boot:run
