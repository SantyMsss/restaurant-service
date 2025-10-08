# 🐳 Docker - Guía de Uso

## 📋 Características del Dockerfile

### ✨ Buenas Prácticas Implementadas:

1. **Multi-stage Build**: Separa la construcción de la ejecución
   - Imagen más pequeña (solo JRE en runtime)
   - Mayor seguridad
   - Build cache optimizado

2. **Imagen Base Ligera**: 
   - `eclipse-temurin:17-jdk-alpine` para build (~200MB)
   - `eclipse-temurin:17-jre-alpine` para runtime (~150MB)

3. **Seguridad**:
   - Usuario no-root (`spring`)
   - Imagen oficial de Eclipse Temurin
   - Sin permisos de administrador

4. **Optimización**:
   - Cache de dependencias Maven
   - `.dockerignore` para reducir contexto
   - Variables de entorno configurables

5. **Monitoreo**:
   - Healthcheck integrado
   - Spring Boot Actuator

## 🚀 Comandos Docker

### Construir y ejecutar todo
```bash
docker-compose up --build
```

### Ejecutar en segundo plano
```bash
docker-compose up -d
```

### Ver logs
```bash
# Todos los servicios
docker-compose logs -f

# Solo la aplicación
docker-compose logs -f app

# Solo PostgreSQL
docker-compose logs -f postgres
```

### Detener servicios
```bash
docker-compose down
```

### Detener y eliminar volúmenes (¡cuidado! elimina datos)
```bash
docker-compose down -v
```

### Reconstruir solo la aplicación
```bash
docker-compose up --build app
```

### Ver estado de contenedores
```bash
docker-compose ps
```

## 📊 Endpoints Disponibles

- **API**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **PostgreSQL**: localhost:5432

## 🔧 Configuración Personalizada

### Cambiar memoria JVM
En `docker-compose.yml`:
```yaml
environment:
  JAVA_OPTS: "-Xms512m -Xmx1024m"
```

### Cambiar configuración de base de datos
En `docker-compose.yml`:
```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/mi_bd
  SPRING_DATASOURCE_USERNAME: mi_usuario
  SPRING_DATASOURCE_PASSWORD: mi_password
```

## 🛠️ Troubleshooting

### La aplicación no inicia
1. Verificar logs: `docker-compose logs app`
2. Verificar que PostgreSQL esté saludable: `docker-compose ps`

### Error de conexión a base de datos
1. Esperar a que PostgreSQL esté listo (healthcheck automático)
2. Verificar variables de entorno en `docker-compose.yml`

### Reconstruir desde cero
```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up
```

## 📦 Arquitectura

```
┌─────────────────────────────────────┐
│   Docker Compose Network            │
│                                     │
│  ┌──────────────┐  ┌─────────────┐ │
│  │              │  │             │ │
│  │  App         │──│ PostgreSQL  │ │
│  │  (Port 8080) │  │ (Port 5432) │ │
│  │              │  │             │ │
│  └──────────────┘  └─────────────┘ │
│                         │           │
│                         ▼           │
│                   ┌──────────┐      │
│                   │ Volume   │      │
│                   │ postgres │      │
│                   └──────────┘      │
└─────────────────────────────────────┘
```

## 🎯 Próximos Pasos

- [ ] Configurar variables de entorno en archivo `.env`
- [ ] Agregar nginx como reverse proxy
- [ ] Implementar logs centralizados
- [ ] Configurar CI/CD con GitHub Actions
