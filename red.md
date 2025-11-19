gestion-colis/
├─ src/main/java/com/example/gestioncolis/
│   ├─ config/
│   │   ├─ SecurityConfig.java
│   │   ├─ JwtAuthFilter.java
│   │   └─ SwaggerConfig.java
│   ├─ controller/
│   │   ├─ auth/AuthController.java
│   │   ├─ admin/ColisAdminController.java
│   │   └─ transporteur/ColisTransporteurController.java
│   ├─ dto/
│   │   ├─ user/UserDTO.java
│   │   ├─ colis/ColisDTO.java
│   ├─ exception/
│   │   ├─ ApiException.java
│   │   └─ GlobalExceptionHandler.java
│   ├─ mapper/
│   │   └─ ColisMapper.java
│   ├─ model/
│   │   ├─ User.java
│   │   ├─ Colis.java
│   │   ├─ enums/Role.java
│   │   ├─ enums/ColisType.java
│   │   └─ enums/StatutColis.java
│   ├─ repository/
│   │   ├─ UserRepository.java
│   │   └─ ColisRepository.java
│   ├─ security/
│   │   └─ JwtUtil.java
│   ├─ service/
│   │   ├─ impl/UserServiceImpl.java
│   │   └─ impl/ColisServiceImpl.java
│   └─ Application.java
├─ src/test/java/...
├─ Dockerfile
├─ docker-compose.yml
├─ .github/workflows/ci.yml
├─ README.md
└─ plantuml/ (use-case.puml, classes.puml)
