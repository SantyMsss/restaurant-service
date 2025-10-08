# ========================================
# Script para EXPORTAR la aplicación
# ========================================
# Este script empaqueta todo lo necesario
# para ejecutar en otra máquina
# ========================================

Write-Host "📦 Empaquetando Restaurant Service para distribución..." -ForegroundColor Cyan
Write-Host ""

# 1. Construir la imagen
Write-Host "🔨 Paso 1/3: Construyendo imagen Docker..." -ForegroundColor Yellow
docker-compose build
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al construir la imagen" -ForegroundColor Red
    exit 1
}

# 2. Exportar la imagen
Write-Host "💾 Paso 2/3: Exportando imagen a archivo..." -ForegroundColor Yellow
docker save restaurant-service-app:latest -o restaurant-app.tar
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al exportar la imagen" -ForegroundColor Red
    exit 1
}

# 3. Crear carpeta de distribución
Write-Host "📁 Paso 3/3: Creando paquete de distribución..." -ForegroundColor Yellow
$distFolder = ".\distribution"
if (Test-Path $distFolder) {
    Remove-Item $distFolder -Recurse -Force
}
New-Item -ItemType Directory -Path $distFolder | Out-Null

# Copiar archivos necesarios
Copy-Item "restaurant-app.tar" -Destination "$distFolder\"
Copy-Item "docker-compose.production.yml" -Destination "$distFolder\docker-compose.yml"

# Crear README para distribución
$readmeContent = @"
# Restaurant Service - Distribución

## 🚀 Inicio Rápido

### Requisitos
- Docker Desktop instalado
- Puertos 8080 y 5432 disponibles

### Instrucciones

1. Cargar la imagen Docker:
``````powershell
docker load -i restaurant-app.tar
``````

2. Iniciar la aplicación:
``````powershell
docker-compose up -d
``````

3. Acceder a la aplicación:
- API: http://localhost:8080
- Health: http://localhost:8080/actuator/health

### Detener la aplicación
``````powershell
docker-compose down
``````

### Ver logs
``````powershell
docker-compose logs -f
``````

---
Para más información: https://github.com/SantyMsss/restaurant-service
"@

Set-Content -Path "$distFolder\README.md" -Value $readmeContent

# Crear script de instalación para la otra máquina
$deployScript = @"
# Script de despliegue automático
Write-Host "🚀 Desplegando Restaurant Service..." -ForegroundColor Cyan

# Cargar imagen
Write-Host "📥 Cargando imagen Docker..." -ForegroundColor Yellow
docker load -i restaurant-app.tar

# Iniciar servicios
Write-Host "▶️  Iniciando servicios..." -ForegroundColor Yellow
docker-compose up -d

# Esperar a que inicie
Write-Host "⏳ Esperando a que la aplicación inicie..." -ForegroundColor Yellow
Start-Sleep -Seconds 15

# Verificar estado
Write-Host ""
Write-Host "✅ Verificando estado de los servicios..." -ForegroundColor Green
docker-compose ps

Write-Host ""
Write-Host "🎉 ¡Aplicación desplegada exitosamente!" -ForegroundColor Green
Write-Host "📍 API disponible en: http://localhost:8080" -ForegroundColor Cyan
Write-Host "💚 Health check: http://localhost:8080/actuator/health" -ForegroundColor Cyan
"@

Set-Content -Path "$distFolder\deploy.ps1" -Value $deployScript

# Mostrar resumen
Write-Host ""
Write-Host "✅ ¡Paquete creado exitosamente!" -ForegroundColor Green
Write-Host ""
Write-Host "📦 Carpeta de distribución: $distFolder" -ForegroundColor Cyan
Write-Host ""
Write-Host "📋 Archivos incluidos:" -ForegroundColor Yellow
Write-Host "   ✓ restaurant-app.tar (imagen Docker)" -ForegroundColor White
Write-Host "   ✓ docker-compose.yml (configuración)" -ForegroundColor White
Write-Host "   ✓ README.md (instrucciones)" -ForegroundColor White
Write-Host "   ✓ deploy.ps1 (script de despliegue)" -ForegroundColor White
Write-Host ""
Write-Host "📤 Pasos siguientes:" -ForegroundColor Yellow
Write-Host "   1. Comprimir la carpeta '$distFolder'" -ForegroundColor White
Write-Host "   2. Enviar a la otra máquina" -ForegroundColor White
Write-Host "   3. Descomprimir y ejecutar: .\deploy.ps1" -ForegroundColor White
Write-Host ""

# Calcular tamaño
$size = (Get-Item "$distFolder\restaurant-app.tar").Length / 1MB
Write-Host "💾 Tamaño del paquete: $([math]::Round($size, 2)) MB" -ForegroundColor Cyan
Write-Host ""
