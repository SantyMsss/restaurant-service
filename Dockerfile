# ====================================
# Etapa 1: Build (Construcción)
# ====================================
FROM eclipse-temurin:17-jdk-alpine AS builder

# Instalar Maven
RUN apk add --no-cache maven

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivo pom.xml para cachear dependencias
COPY pom.xml .

# Descargar dependencias (capa cacheada)
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src src

# Construir la aplicación
RUN mvn clean package -DskipTests -B

# ====================================
# Etapa 2: Runtime (Ejecución)
# ====================================
FROM eclipse-temurin:17-jre-alpine

# Información de la imagen
LABEL maintainer="restaurant-service"
LABEL description="Restaurant Service API - Spring Boot Application"

# Crear usuario no-root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring

# Instalar wget para healthcheck
RUN apk add --no-cache wget

# Establecer directorio de trabajo
WORKDIR /app

# Copiar solo el JAR desde la etapa de build
COPY --from=builder /app/target/restaurant-service-0.0.1-SNAPSHOT.jar app.jar

# Cambiar propietario del archivo
RUN chown spring:spring app.jar

# Cambiar a usuario no-root
USER spring:spring

# Exponer puerto
EXPOSE 8080

# Variables de entorno opcionales
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Comando para ejecutar con opciones JVM optimizadas
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]