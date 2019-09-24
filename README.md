# Trabalho de Sistemas de Informação
- Sistema de autenticação feio em Spring Boot;
- Jwt;
- BCryptPasswordEncoder;
- Hibernate;

# Postman Rotas
  # Registro
    http://localhost:3000/auth/signin
    # Objeto
      {
        "username": "username",
        "senha": "123456",
        "perfil": "USER"
      }
  # Login
    http://localhost:3000/auth/signup
    # Objeto
      {
        "username": "username",
        "senha": "123456",
      }
