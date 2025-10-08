# 🐳 Guía Rápida - Docker Restaurant Service

## ✅ Estado Actual
- ✅ Dockerfile optimizado con multi-stage build
- ✅ docker-compose.yml configurado
- ✅ Aplicación corriendo en http://localhost:8080
- ✅ PostgreSQL corriendo en localhost:5432
- ✅ Healthchecks funcionando correctamente

## 🚀 Inicio Rápido

### 1. Iniciar Todo
```powershell
docker-compose up -d
```

### 2. Ver Logs
```powershell
# Todos los servicios
docker-compose logs -f

# Solo la app
docker-compose logs -f app
```

### 3. Verificar Estado
```powershell
docker-compose ps
```

### 4. Probar la API
```powershell
# Health check
Invoke-RestMethod http://localhost:8080/actuator/health

# Listar restaurantes
Invoke-RestMethod http://localhost:8080/api/restaurantes
```

### 5. Detener Todo
```powershell
docker-compose down
```

## 📋 Comandos Frecuentes

### Reconstruir después de cambios
```powershell
docker-compose up --build
```

### Ver logs históricos
```powershell
docker-compose logs app
```

### Reiniciar solo la app
```powershell
docker-compose restart app
```

### Eliminar todo (incluyendo datos)
```powershell
docker-compose down -v
```

### Acceder al shell del contenedor
```powershell
docker exec -it restaurant-app sh
```

### Ver uso de recursos
```powershell
docker stats
```

## 🔍 Endpoints Disponibles

| Endpoint | Descripción |
|----------|-------------|
| http://localhost:8080 | API Principal |
| http://localhost:8080/actuator/health | Health Check |
| http://localhost:8080/api/restaurantes | Lista de restaurantes |
| http://localhost:8080/api/usuarios | Usuarios |
| http://localhost:8080/api/mesas | Mesas |
| http://localhost:8080/api/pedidos | Pedidos |

## 🛠️ Solución de Problemas

### La app no inicia
```powershell
# Ver logs detallados
docker-compose logs app

# Verificar PostgreSQL
docker-compose logs postgres
```

### Puerto ya en uso
```powershell
# Cambiar puerto en docker-compose.yml
ports:
  - "8081:8080"  # Usar 8081 en lugar de 8080
```

### Limpiar todo y empezar de nuevo
```powershell
docker-compose down -v
docker system prune -f
docker-compose up --build
```

## 📊 Información de las Imágenes

### Tamaños
```powershell
docker images | Select-String "restaurant"
```

### Espacio usado
```powershell
docker system df
```

## 🎯 Buenas Prácticas Implementadas

✅ Multi-stage build (imagen pequeña)
✅ Usuario no-root (seguridad)
✅ Healthchecks automáticos
✅ Variables de entorno configurables
✅ Volúmenes persistentes para datos
✅ Red dedicada para aislamiento
✅ Cache optimizado de dependencias
✅ .dockerignore para contexto limpio

## 📚 Documentación Completa

- `DOCKER.md` - Guía completa de Docker
- `DOCKER-SUMMARY.md` - Resumen de implementación
- `README.md` - Documentación del proyecto

---
¡Listo para producción! 🎉
