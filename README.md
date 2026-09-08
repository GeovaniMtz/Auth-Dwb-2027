# Auth Service

Microservicio de autenticación construido con **Spring Boot 4** y **Java 21**. Expone una API REST para el registro y la consulta de usuarios, persistidos en **MySQL** mediante **Spring Data JPA**.

## Stack

| Componente        | Tecnología                          |
|--------------------|--------------------------------------|
| Lenguaje           | Java 21                              |
| Framework          | Spring Boot 4.1.1                    |
| Persistencia       | Spring Data JPA + MySQL              |
| Seguridad          | Spring Security                      |
| Utilidades         | Lombok                               |
| Build              | Maven (con wrapper `mvnw`)           |

## Estructura del proyecto

```
src/main/java/com/dwb/auth
├── AuthApplication.java     # Punto de entrada de la app
├── controller
│   └── CtrlUser.java        # Endpoints REST de usuario
├── entity
│   └── User.java            # Entidad JPA (id, username, email, password)
├── repo
│   └── RepoUser.java        # Repositorio JPA
└── service
    ├── SvcUser.java         # Contrato del servicio
    └── SvcUserImp.java      # Implementación del servicio
```

### Requisitos previos

- Java 21+
- MySQL en ejecución (base de datos accesible localmente)
- No necesitas tener Maven instalado: el proyecto incluye el wrapper (`mvnw` / `mvnw.cmd`)

### 1. Configura la base de datos

Ajusta las credenciales y la URL de conexión en [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
server.port=8082
spring.application.name=auth

spring.datasource.url=jdbc:mysql://localhost:3306/<tu_base_de_datos>
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_password>
```

### 2. Ejecuta la aplicación

```bash
./mvnw spring-boot:run
```

La app quedará disponible en `http://localhost:8082`.


## Endpoints

Base path: `/user`

| Método | Ruta     | Descripción                  | Body                                  |
|--------|----------|-------------------------------|----------------------------------------|
| `POST` | `/user`  | Crea un nuevo usuario         | `{ "username", "email", "password" }` |
| `GET`  | `/user`  | Lista todos los usuarios      | —                                      |

#### Ejemplo — crear usuario

```bash
curl -X POST http://localhost:8082/user \
  -H "Content-Type: application/json" \
  -d '{"username":"geo","email":"geo@example.com","password":"1234"}'
```

#### Ejemplo — listar usuarios

```bash
curl http://localhost:8082/user
```
