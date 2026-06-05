e-Cuti Microservices

Sistem manajemen cuti berbasis Microservices menggunakan Spring Boot 3, Spring Cloud, Spring Security OAuth2 Authorization Server, dan PostgreSQL.

🏗 Arsitektur Sistem

Sistem terdiri dari beberapa service:

Service	Port	Deskripsi
Config Server	8888	Centralized Configuration
Eureka Server	8761	Service Discovery
Auth Service	9000	OAuth2 Authorization Server & Authentication
Employee Service	8081	Data Karyawan
Permission Service	8082	Data Perizinan / Cuti
📦 Tech Stack
Java 21
Spring Boot 3
Spring Cloud
Spring Security OAuth2 Authorization Server
Spring Data JPA
PostgreSQL
Eureka Discovery Server
Config Server
OpenAPI / Swagger
Maven
🚀 Menjalankan Project
1. Buat Database
CREATE DATABASE auth_db;
CREATE DATABASE employee_db;
CREATE DATABASE permission_db;
2. Jalankan Config Server
cd config-server
mvn spring-boot:run

Akses:

http://localhost:8888
3. Jalankan Eureka Server
cd discovery-service
mvn spring-boot:run

Akses:

http://localhost:8761
4. Jalankan Auth Service
cd auth-service
mvn spring-boot:run

Akses:

http://localhost:9000
5. Jalankan Employee Service
cd employee-service
mvn spring-boot:run
6. Jalankan Permission Service
cd permission-service
mvn spring-boot:run
🔐 Authentication Flow

Project menggunakan:

OAuth2 Authorization Server
Authorization Code Flow
JWT Token (RS256)

Issuer:

http://192.168.1.5:9000
👤 Register User

Swagger Auth Service:

http://localhost:9000/swagger-ui/index.html#/

Endpoint:

POST /auth/register

Request:

{
  "username": "enggita",
  "email": "enggita@gmail.com",
  "password": "password123"
}

Response:

{
  "message": "User registered successfully"
}
🔑 Login User

Buka browser:

http://192.168.1.5:9000/login

Akan muncul halaman login Spring Security:

Masukkan:

Username : enggita
Password : password123

Klik:

Sign In
🔐 Mendapatkan Authorization Code

Setelah login berhasil, buka URL berikut:

http://192.168.1.5:9000/oauth2/authorize?response_type=code&client_id=ecuti-client&redirect_uri=http://127.0.0.1:8082/login/oauth2/code/ecuti&scope=openid

Login menggunakan user yang sudah diregister.

Setelah berhasil login, browser akan redirect:

http://127.0.0.1:8082/login/oauth2/code/ecuti?code=xxxxxxxxxxxxxxxx

Salin value:

code=xxxxxxxxxxxxxxxx
🎟 Exchange Authorization Code Menjadi Access Token

Gunakan Postman atau Curl:

curl -X POST "http://192.168.1.5:9000/oauth2/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "ecuti-client:secret" \
-d "grant_type=authorization_code" \
-d "code=ISI_CODE_DISINI" \
-d "redirect_uri=http://127.0.0.1:8082/login/oauth2/code/ecuti"
Response
{
  "access_token": "eyJraWQiOiI...",
  "refresh_token": "eyJraWQiOiI...",
  "scope": "openid",
  "token_type": "Bearer",
  "expires_in": 300
}
🧪 Testing Employee Service

Endpoint:

http://localhost:8081/api/employees

Header:

Authorization: Bearer ACCESS_TOKEN

Contoh:

GET /api/employees
🧪 Testing Permission Service

Endpoint:

http://localhost:8082/api/permissions

Header:

Authorization: Bearer ACCESS_TOKEN

Contoh:

GET /api/permissions
📖 Swagger Documentation
Auth Service
http://localhost:9000/swagger-ui/index.html
Employee Service
http://localhost:8081/swagger-ui/index.html
Permission Service
http://localhost:8082/swagger-ui/index.html
⚙ Configuration Server

Semua service menggunakan konfigurasi dari Config Server:

spring:
  config:
    import: optional:configserver:http://localhost:8888/
🔍 Service Discovery

Setiap service akan otomatis register ke Eureka:

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

Dashboard Eureka:

http://localhost:8761
📝 Development Notes
JWK Configuration

Gunakan RSA Key Pair yang statis.

Jangan menggunakan:

UUID.randomUUID()

untuk generate key saat startup karena token lama akan menjadi invalid setelah restart service.

Hibernate

Untuk development:

spring:
  jpa:
    hibernate:
      ddl-auto: update

Agar schema database otomatis dibuat dan diperbarui.

🌟 Future Improvements
API Gateway Routing
Role Based Access Control (RBAC)
Refresh Token Rotation
Unit Testing (JUnit + Mockito)
Docker & Docker Compose
Kubernetes Deployment
CI/CD GitHub Actions
👨‍💻 Author

Wahyu Aji Saputro

Software Engineer | Spring Boot | Microservices | Flutter Developer
