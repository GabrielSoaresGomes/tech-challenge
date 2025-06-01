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

## 🏛️ Arquitetura do Projeto
A arquitetura utilizada no projeto segue o padrão MVC (Model-View-Controller), com uma separação clara de responsabilidades entre camadas como controller, service, repository, config e dtos. Essa organização favorece a manutenção e a extensibilidade do código, além de promover boas práticas de desenvolvimento orientado a objetos.

Além disso, a estrutura foi desenhada com base em princípios do DDD (Domain-Driven Design) e nos princípios SOLID, garantindo um código desacoplado, testável e coeso.

Toda a aplicação é conteinerizada com Docker e orquestrada com Docker Compose, permitindo a replicação do ambiente em diferentes máquinas com facilidade. O banco de dados utilizado é o PostgreSQL, rodando em um contêiner isolado.

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
┃ ┃ ┣ 📁 domains
┃ ┃ ┃ ┣📄 User
┃ ┃ ┃ ┗📄 Address
┃ ┃ ┣ 📁 entities
┃ ┃ ┃ ┣📄 UserEntity
┃ ┃ ┃ ┗📄 AddressEntity 
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

