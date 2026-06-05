# e-Cuti Microservices

Sistem manajemen cuti berbasis **Microservices** menggunakan **Spring Boot 3**, **Spring Cloud**, **Spring Security OAuth2 Authorization Server**, dan **PostgreSQL**.

---

# 🏗 Arsitektur Sistem

Sistem terdiri dari beberapa service:

| Service | Port | Deskripsi |
|----------|----------|----------|
| Config Server | 8888 | Centralized Configuration |
| Eureka Server | 8761 | Service Discovery |
| Auth Service | 9000 | OAuth2 Authorization Server & Authentication |
| Employee Service | 8081 | Data Karyawan |
| Permission Service | 8082 | Data Perizinan / Cuti |

---

# 📦 Tech Stack

- Java 21
- Spring Boot 3
- Spring Cloud
- Spring Security OAuth2 Authorization Server
- Spring Data JPA
- PostgreSQL
- Eureka Discovery Server
- Config Server
- OpenAPI / Swagger
- Maven

---

# 🚀 Menjalankan Project

## 1. Buat Database

```sql
CREATE DATABASE auth_db;
CREATE DATABASE employee_db;
CREATE DATABASE permission_db;
```

---

## 2. Jalankan Config Server

```bash
cd config-server
mvn spring-boot:run
```

Akses:

```text
http://localhost:8888
```

---

## 3. Jalankan Eureka Server

```bash
cd discovery-service
mvn spring-boot:run
```

Akses:

```text
http://localhost:8761
```

---

## 4. Jalankan Auth Service

```bash
cd auth-service
mvn spring-boot:run
```

Akses:

```text
http://localhost:9000
```

---

## 5. Jalankan Employee Service

```bash
cd employee-service
mvn spring-boot:run
```

---

## 6. Jalankan Permission Service

```bash
cd permission-service
mvn spring-boot:run
```

---

# 🔐 Authentication Flow

Project menggunakan:

- OAuth2 Authorization Server
- Authorization Code Flow
- JWT Token (RS256)

Issuer:

```text
http://192.168.1.5:9000
```

---

# 👤 Register User

## Swagger Auth Service

```text
http://localhost:9000/swagger-ui/index.html
```

### Endpoint

```http
POST /auth/register
```

### Request

```json
{
  "username": "enggita",
  "email": "enggita@gmail.com",
  "password": "password123"
}
```

### Response

```json
{
  "message": "User registered successfully"
}
```

---

# 🔑 Login User

Buka browser:

```text
http://192.168.1.5:9000/login
```

Masukkan:

```text
Username : enggita
Password : password123
```

Klik tombol:

```text
Sign In
```

---

# 🔐 Mendapatkan Authorization Code

Setelah login berhasil, buka URL berikut:

```text
http://192.168.1.5:9000/oauth2/authorize?response_type=code&client_id=ecuti-client&redirect_uri=http://127.0.0.1:8082/login/oauth2/code/ecuti&scope=openid
```

Jika berhasil login, browser akan redirect ke:

```text
http://127.0.0.1:8082/login/oauth2/code/ecuti?code=xxxxxxxxxxxxxxxx
```

Salin nilai:

```text
code=xxxxxxxxxxxxxxxx
```

---

# 🎟 Exchange Authorization Code Menjadi Access Token

## Menggunakan Curl

```bash
curl -X POST "http://192.168.1.5:9000/oauth2/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "ecuti-client:secret" \
-d "grant_type=authorization_code" \
-d "code=ISI_CODE_DISINI" \
-d "redirect_uri=http://127.0.0.1:8082/login/oauth2/code/ecuti"
```

---

## Menggunakan Postman

### Method

```text
POST
```

### URL

```text
http://192.168.1.5:9000/oauth2/token
```

### Authorization

```text
Basic Auth
```

| Username | Password |
|-----------|-----------|
| ecuti-client | secret |

### Body

Pilih:

```text
x-www-form-urlencoded
```

Isi:

| Key | Value |
|------|------|
| grant_type | authorization_code |
| code | hasil code dari authorize |
| redirect_uri | http://127.0.0.1:8082/login/oauth2/code/ecuti |

---

## Response

```json
{
  "access_token": "eyJraWQiOiI...",
  "refresh_token": "eyJraWQiOiI...",
  "scope": "openid",
  "token_type": "Bearer",
  "expires_in": 300
}
```

---

# 🧪 Testing Employee Service

### Endpoint

```text
http://localhost:8081/api/employees
```

### Header

```http
Authorization: Bearer ACCESS_TOKEN
```

### Contoh Request

```http
GET /api/employees
```

---

# 🧪 Testing Permission Service

### Endpoint

```text
http://localhost:8082/api/permissions
```

### Header

```http
Authorization: Bearer ACCESS_TOKEN
```

### Contoh Request

```http
GET /api/permissions
```

---

# 📖 Swagger Documentation

## Auth Service

```text
http://localhost:9000/swagger-ui/index.html
```

## Employee Service

```text
http://localhost:8081/swagger-ui/index.html
```

## Permission Service

```text
http://localhost:8082/swagger-ui/index.html
```

---

# ⚙ Configuration Server

Seluruh service mengambil konfigurasi dari Config Server:

```yaml
spring:
  config:
    import: optional:configserver:http://localhost:8888/
```

---

# 🔍 Service Discovery

Seluruh service akan otomatis register ke Eureka:

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

Dashboard Eureka:

```text
http://localhost:8761
```

---

# 📝 Development Notes

## JWK Configuration

Gunakan RSA Key Pair yang statis.

Jangan menggunakan:

```java
UUID.randomUUID()
```

untuk generate key saat startup karena token lama akan menjadi invalid setelah restart service.

---

## Hibernate

Untuk development:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

Agar schema database otomatis dibuat dan diperbarui.

---

# 🌟 Future Improvements

- API Gateway Routing
- Role Based Access Control (RBAC)
- Refresh Token Rotation
- Unit Testing (JUnit + Mockito)
- Docker & Docker Compose
- Kubernetes Deployment
- CI/CD GitHub Actions

---

# ✅ Requirement Checklist

| Requirement | Status |
|------------|---------|
| Spring Cloud Config | ✅ |
| Eureka Discovery Server | ✅ |
| OAuth2 Authorization Server | ✅ |
| Employee CRUD Service | ✅ |
| Permission CRUD Service | ✅ |
| Swagger/OpenAPI | ✅ |
| PostgreSQL | ✅ |
| Microservices Architecture | ✅ |
| Service Registration via Eureka | ✅ |

---

# 👨‍💻 Author

**Wahyu Aji Saputro**

Software Engineer | Spring Boot | Microservices | Flutter Developer
