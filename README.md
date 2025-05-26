# 🛡️ Cadastro de Usuários e Endereços

Projeto backend desenvolvido em Java com Spring Boot 3.4.4 e Java 21. Esta aplicação tem como objetivo realizar o cadastro de usuários e seus respectivos endereços. No momento, o sistema permite autenticação de usuários e alteração de senha.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Swagger
- Maven

## 📦 Estrutura do Projeto
```
📁 src
┣ 📁 main
┃ ┣ 📁 java
┃ ┃ ┗ 📁 com.postech.challange_01
┃ ┃ ┣ 📁 config
┃ ┃ ┣ 📁 controllers
┃ ┃ ┃ ┣📁 handlers
┃ ┃ ┃ ┣📁 security
┃ ┃ ┃ ┣📄 UserController
┃ ┃ ┃ ┗📄 AddressController
┃ ┃ ┣ 📁 dtos
┃ ┃ ┃ ┣📁 exceptions
┃ ┃ ┃ ┣📁 requests
┃ ┃ ┃ ┣📁 responses
┃ ┃ ┃ ┗📁 security
┃ ┃ ┣ 📁 entities
┃ ┃ ┃ ┣📄 User
┃ ┃ ┃ ┗📄 Address
┃ ┃ ┣ 📁 exceptions
┃ ┃ ┣ 📁 mappers
┃ ┃ ┣ 📁 repositories
┃ ┃ ┗ 📁 usecases
┃ ┃   ┣ 📁 rules
┃ ┃   ┣ 📁 security
┃ ┃   ┣ 📁 user
┃ ┃   ┗ 📁 address
┃ ┗ 📁 resources
┃   ┣ 📄 application.properties
┃   ┗ 📄 data.sql
┗ 📄 Dockerfile
```

## ⚙️ Funcionalidades

- Cadastro de usuários
- Cadastro de endereços
- Autenticação e validação de acesso
- Alteração de senha

## 🐳 Subindo o Projeto com Docker Compose

1. **Pré-requisitos**:
   - Docker e Docker Compose instalados

2. **Subir o ambiente**:
   -  execute: docker-compose up --build

