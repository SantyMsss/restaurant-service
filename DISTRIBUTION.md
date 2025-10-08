# 🌐 Guía de Distribución - Restaurant Service

## 🎯 Tres Formas de Ejecutar en Otra Máquina

### ✅ Opción 1: Con Código Fuente (Desarrollo)
**Requisitos**: Docker Desktop instalado

```powershell
# 1. Clonar el repositorio
git clone https://github.com/SantyMsss/restaurant-service.git
cd restaurant-service

# 2. Ejecutar
docker-compose up -d
```

**Pros**: ✅ Más fácil para desarrollo  
**Contras**: ❌ Requiere todo el código fuente

---

### 🚀 Opción 2: Solo con Docker Compose (Recomendado)
**Requisitos**: Docker Desktop instalado

#### Paso 1: Construir la imagen localmente
En tu máquina actual:
```powershell
# Construir la imagen
docker-compose build

# Exportar la imagen a un archivo
docker save restaurant-service-app:latest -o restaurant-app.tar
```

#### Paso 2: En la otra máquina
```powershell
# Cargar la imagen
docker load -i restaurant-app.tar

# Copiar el archivo docker-compose.production.yml
# Renombrarlo a docker-compose.yml

# Ejecutar
docker-compose up -d
```

**Pros**: ✅ No necesita código fuente, ✅ Todo incluido  
**Contras**: ❌ Archivo .tar puede ser grande (~300MB)

---

### 🌟 Opción 3: Publicar en Docker Hub (MEJOR para Producción)
**Requisitos**: Cuenta en Docker Hub (gratis)

#### Paso 1: Crear cuenta en Docker Hub
1. Ve a https://hub.docker.com
2. Crea una cuenta gratuita

#### Paso 2: Publicar la imagen
```powershell
# Login en Docker Hub
docker login

# Construir con tag de tu usuario
docker build -t tunombre/restaurant-service:1.0.0 .

# Publicar
docker push tunombre/restaurant-service:1.0.0
```

#### Paso 3: En cualquier máquina del mundo
```powershell
# Descargar solo el docker-compose.production.yml
# Editar la línea: image: tunombre/restaurant-service:1.0.0

# Ejecutar (descarga automáticamente desde Docker Hub)
docker-compose -f docker-compose.production.yml up -d
```

**Pros**: ✅ Más profesional, ✅ Versionado, ✅ Descarga automática  
**Contras**: ⚠️ Imagen pública (o pagar por privada)

---

## 📋 Comparación Rápida

| Método | Requiere Código | Tamaño Descarga | Facilidad | Profesional |
|--------|-----------------|-----------------|-----------|-------------|
| Con código fuente | ✅ Sí | ~50KB | ⭐⭐⭐ | ⭐ |
| Archivo .tar | ❌ No | ~300MB | ⭐⭐⭐⭐ | ⭐⭐ |
| Docker Hub | ❌ No | ~150MB | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |

---

## 🔧 Scripts de Ayuda

### Para empaquetar todo (Opción 2)
```powershell
# Archivo: package.ps1
docker-compose build
docker save restaurant-service-app:latest -o restaurant-app.tar
Write-Host "✅ Imagen exportada a restaurant-app.tar"
Write-Host "📦 Copia estos archivos a la otra máquina:"
Write-Host "   - restaurant-app.tar"
Write-Host "   - docker-compose.production.yml"
```

### Para desplegar en otra máquina (Opción 2)
```powershell
# Archivo: deploy.ps1
Write-Host "🚀 Desplegando Restaurant Service..."
docker load -i restaurant-app.tar
docker-compose -f docker-compose.production.yml up -d
Write-Host "✅ Aplicación corriendo en http://localhost:8080"
```

---

## 🌍 Ejemplo Real: Docker Hub

### 1. Configurar tu imagen
```powershell
# Construir con tu nombre de usuario
docker build -t santymss/restaurant-service:1.0.0 .

# Login
docker login

# Publicar
docker push santymss/restaurant-service:1.0.0
```

### 2. Crear archivo para distribución
**Archivo: `docker-compose.yml`** (para compartir)
```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: restaurant
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 0809
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  app:
    image: santymss/restaurant-service:1.0.0
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/restaurant

volumes:
  postgres_data:
```

### 3. En cualquier máquina
```powershell
# Solo necesitan este archivo docker-compose.yml
docker-compose up -d

# Docker descarga automáticamente todo lo necesario
```

---

## ✅ Checklist para la Otra Máquina

### Requisitos Mínimos
- [ ] Docker Desktop instalado
- [ ] 4GB RAM disponible
- [ ] 2GB espacio en disco
- [ ] Puerto 8080 libre
- [ ] Puerto 5432 libre (o cambiar en docker-compose)

### Archivos Necesarios (según método)

**Opción 1 (Código fuente)**:
- [ ] Todo el repositorio

**Opción 2 (Archivo .tar)**:
- [ ] `restaurant-app.tar`
- [ ] `docker-compose.production.yml`

**Opción 3 (Docker Hub)**:
- [ ] Solo `docker-compose.production.yml`

---

## 🆘 Solución de Problemas

### "Error: image not found"
```powershell
# Si usas archivo .tar
docker load -i restaurant-app.tar

# Si usas Docker Hub
docker pull tunombre/restaurant-service:1.0.0
```

### "Port already in use"
Editar `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Cambiar 8080 por otro puerto
```

### "Cannot connect to database"
```powershell
# Verificar que PostgreSQL esté corriendo
docker-compose ps

# Ver logs
docker-compose logs postgres
```

---

## 📊 Flujo Recomendado para Producción

```
┌─────────────────┐
│  Tu Máquina     │
│  (Desarrollo)   │
└────────┬────────┘
         │
         │ docker build + docker push
         ▼
┌─────────────────┐
│   Docker Hub    │
│  (Almacenamiento)│
└────────┬────────┘
         │
         │ docker-compose up
         ▼
┌─────────────────┐
│  Otra Máquina   │
│  (Producción)   │
└─────────────────┘
```

---

## 🎓 Próximos Pasos

1. [ ] Crear cuenta en Docker Hub
2. [ ] Publicar primera versión
3. [ ] Probar en otra máquina
4. [ ] Configurar CI/CD (GitHub Actions)
5. [ ] Implementar versionado semántico (1.0.0, 1.1.0, etc.)

---

**¿Necesitas ayuda?** Revisa `DOCKER.md` para más detalles.
