# Arquitectura de Microservicios - Linktic

Este proyecto está compuesto por múltiples microservicios que se comunican entre sí utilizando una arquitectura basada en Spring Boot. A continuación se describe el propósito de cada microservicio, su rol en el sistema y cómo levantar todo el ecosistema con el script `start.sh`.

## 📦 Microservicios

### 1. `microservice-api-gateway`
**Descripción:**  
Este microservicio actúa como punto de entrada único para el sistema. Redirige las solicitudes entrantes hacia los microservicios correspondientes utilizando rutas definidas. También puede encargarse de la autenticación, autorización, rate limiting, entre otros.

**Responsabilidades:**
- Encaminamiento de solicitudes (routing)
- Seguridad (con JWT, en este caso no se implementó por falta de tiempo)
- Manejo de CORS
- Centralización de logs y errores

---

### 2. `microservice-eureka`
**Descripción:**  
Servidor de descubrimiento de servicios (Service Registry) usando **Netflix Eureka**. Todos los microservicios se registran aquí y el Gateway lo utiliza para redirigir solicitudes dinámicamente.

**Responsabilidades:**
- Registro y descubrimiento de microservicios
- Balanceo de carga entre instancias
- Alta disponibilidad y resiliencia

---

### 3. `microservice-config-server`
**Descripción:**  
Servidor centralizado de configuración. Proporciona los archivos de configuración (application.yml/properties) para todos los microservicios desde un repositorio Git u otra fuente externa.

**Responsabilidades:**
- Centralización de la configuración de todos los microservicios
- Soporte para múltiples entornos (`dev`, `prod`, etc.)
- Permite actualizaciones en caliente si se implementa con Spring Cloud Bus

---

### 4. `microservice-product`
**Descripción:**  
Gestiona un recurso llamado productos con los campos: id, nombre y precio.

**Responsabilidades:**
- Crear un nuevo producto.
- Obtener un producto específico por ID.
- Actualizar un producto por ID.
- Eliminar un producto por ID.
- Listar todos los productos con paginación simple.

---

### 5. `microservice-inventory`
**Descripción:**  
Gestiona un recurso llamado inventarios con los campos: producto_id y cantidad.

**Responsabilidades:**
- Consultar la cantidad disponible de un producto específico por ID (obtiene la
  información del producto llamando al microservicio de productos).
- Actualizar la cantidad disponible de un producto específico tras una compra.
- Emitir un evento simple cuando el inventario cambia (implementación básica
  puede ser un mensaje en consola).

---

## ⚙️ Arquitectura General

```plaintext
             +---------------------+
             |   Cliente / Front   |
             +---------+-----------+
                       |
                       ▼
             +---------------------+
             |   API Gateway       |
             +---------+-----------+
                       |
     +-----------------+------------------+
     |                                    |
     ▼                                    ▼
+------------+                    +---------------+
| micro-prod |⬅--- Rest API ---➡ | micro-invent. |
+------------+                    +---------------+

Todos los servicios se registran en:
       +--------------------+
       |    Eureka Server   |
       +--------------------+

Las configuraciones provienen de:
       +-------------------------+
       |  Config Server (Git)   |
       +-------------------------+
```
# 🚀 Cómo levantar el proyecto?

Este proyecto está compuesto por varios microservicios desarrollados con Spring Boot. Para facilitar su ejecución local, se incluye un script llamado `start.sh` que inicia todos los servicios en el orden correcto.

## 📁 Microservicios incluidos

- `microservice-config-server`
- `microservice-eureka`
- `microservice-api-gateway`
- `microservice-product`
- `microservice-inventory`

## ✅ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java 17 o superior**
- **Maven**
- **Git**
- **Acceso a internet** (para clonar repositorios si es necesario)
- (Opcional) **Docker**, si utilizas servicios como base de datos o Config Server remoto

## 🔄 Orden de arranque de los microservicios

El orden es importante para garantizar el correcto registro y descubrimiento de servicios:

1. `microservice-config-server` → Provee la configuración a los demás microservicios
2. `microservice-eureka` → Registra los microservicios que se van levantando
3. `microservice-api-gateway` → Puerta de entrada a todo el sistema
4. `microservice-product` → Microservicio de gestión de productos
5. `microservice-inventory` → Microservicio de inventario

## 🧪 ¿Cómo ejecutar todos los servicios?

### Usando el script `start.sh`

Este script automatiza la ejecución de todos los microservicios.

```bash
./start.sh
