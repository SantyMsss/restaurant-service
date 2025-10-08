# ========================================
# Script para PUBLICAR en Docker Hub
# ========================================
# Este script publica tu imagen en Docker Hub
# para que cualquiera pueda descargarla
# ========================================

param(
    [string]$Username = "",
    [string]$Version = "1.0.0"
)

Write-Host "🚀 Publicando Restaurant Service en Docker Hub" -ForegroundColor Cyan
Write-Host ""

# Validar username
if ([string]::IsNullOrWhiteSpace($Username)) {
    Write-Host "❌ Error: Debes proporcionar tu username de Docker Hub" -ForegroundColor Red
    Write-Host ""
    Write-Host "Uso:" -ForegroundColor Yellow
    Write-Host "  .\publish.ps1 -Username tuusuario -Version 1.0.0" -ForegroundColor White
    Write-Host ""
    Write-Host "Ejemplo:" -ForegroundColor Yellow
    Write-Host "  .\publish.ps1 -Username santymss -Version 1.0.0" -ForegroundColor White
    exit 1
}

$imageName = "$Username/restaurant-service"
$imageTag = "$imageName:$Version"
$imageLatest = "$imageName:latest"

Write-Host "📋 Configuración:" -ForegroundColor Yellow
Write-Host "   Usuario: $Username" -ForegroundColor White
Write-Host "   Imagen: $imageName" -ForegroundColor White
Write-Host "   Versión: $Version" -ForegroundColor White
Write-Host ""

# 1. Login en Docker Hub
Write-Host "🔐 Paso 1/4: Login en Docker Hub..." -ForegroundColor Yellow
Write-Host "   (Ingresa tu contraseña cuando se solicite)" -ForegroundColor Gray
docker login
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error en el login" -ForegroundColor Red
    exit 1
}

# 2. Construir la imagen
Write-Host ""
Write-Host "🔨 Paso 2/4: Construyendo imagen..." -ForegroundColor Yellow
docker build -t $imageTag -t $imageLatest .
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al construir la imagen" -ForegroundColor Red
    exit 1
}

# 3. Publicar versión específica
Write-Host ""
Write-Host "📤 Paso 3/4: Publicando versión $Version..." -ForegroundColor Yellow
docker push $imageTag
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al publicar la imagen" -ForegroundColor Red
    exit 1
}

# 4. Publicar latest
Write-Host ""
Write-Host "📤 Paso 4/4: Publicando versión latest..." -ForegroundColor Yellow
docker push $imageLatest
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al publicar latest" -ForegroundColor Red
    exit 1
}

# Crear archivo docker-compose para compartir
Write-Host ""
Write-Host "📝 Creando archivo de distribución..." -ForegroundColor Yellow

$composeContent = @"
# ========================================
# Restaurant Service - Desde Docker Hub
# ========================================
# Ejecutar con: docker-compose up -d
# ========================================

version: '3.8'

services:
  postgres:
    container_name: restaurant-postgres
    image: postgres:15-alpine
    restart: unless-stopped
    environment:
      POSTGRES_DB: restaurant
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 0809
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - restaurant-network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    container_name: restaurant-app
    image: $imageLatest
    restart: unless-stopped
    ports:
      - "8080:8080"
    depends_on:
      postgres:
        condition: service_healthy
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/restaurant
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 0809
      SPRING_JPA_HIBERNATE_DDL_AUTO: create-drop
      JAVA_OPTS: "-Xms256m -Xmx512m"
    networks:
      - restaurant-network
    healthcheck:
      test: ["CMD", "wget", "--no-verbose", "--tries=1", "--spider", "http://localhost:8080/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 40s

networks:
  restaurant-network:
    driver: bridge

volumes:
  postgres_data:
    driver: local
"@

Set-Content -Path "docker-compose.hub.yml" -Value $composeContent

# Éxito
Write-Host ""
Write-Host "✅ ¡Imagen publicada exitosamente en Docker Hub!" -ForegroundColor Green
Write-Host ""
Write-Host "🌐 Tu imagen está disponible en:" -ForegroundColor Cyan
Write-Host "   https://hub.docker.com/r/$imageName" -ForegroundColor White
Write-Host ""
Write-Host "📋 Para usar en otra máquina:" -ForegroundColor Yellow
Write-Host ""
Write-Host "   1. Copia el archivo 'docker-compose.hub.yml' a la otra máquina" -ForegroundColor White
Write-Host "   2. Renómbralo a 'docker-compose.yml'" -ForegroundColor White
Write-Host "   3. Ejecuta: docker-compose up -d" -ForegroundColor White
Write-Host ""
Write-Host "   O simplemente:" -ForegroundColor White
Write-Host "   docker run -p 8080:8080 $imageLatest" -ForegroundColor Cyan
Write-Host ""
Write-Host "📝 Comando para descargar:" -ForegroundColor Yellow
Write-Host "   docker pull $imageLatest" -ForegroundColor Cyan
Write-Host ""
