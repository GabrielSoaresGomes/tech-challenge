# 🍔 Sistema de Gestão de Restaurantes

Projeto backend desenvolvido em Java com Spring Boot 3.4.4 e Java 21. Esta aplicação tem como objetivo gerenciar usuários e restaurantes:

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

Além disso, a estrutura foi desenhada com base em princípios do DDD (Domain-Driven Design), Clean Arch e nos princípios SOLID, garantindo um código desacoplado, testável e coeso.

Toda a aplicação é conteinerizada com Docker e orquestrada com Docker Compose, permitindo a replicação do ambiente em diferentes máquinas com facilidade. O banco de dados utilizado é o PostgreSQL, rodando em um contêiner isolado.

## 📦 Estrutura do Projeto
```
📁 src
┣ 📁 main
┃ ┣ 📁 java
┃ ┃ ┗ 📁 com.postech.challange_01
┃ ┃ ┣ 📁 application
┃ ┃ ┃ ┣📁 mappers
┃ ┃ ┃ ┗📁 usecases
┃ ┃ ┣ 📁 domain
┃ ┃ ┃ ┣📄 Address
┃ ┃ ┃ ┣📄 Menu
┃ ┃ ┃ ┣📄 MenuItem
┃ ┃ ┃ ┣📄 Restaurant
┃ ┃ ┃ ┣📄 User
┃ ┃ ┃ ┣📄 UserAddress
┃ ┃ ┃ ┗📄 UserType
┃ ┃ ┣ 📁 dtos
┃ ┃ ┣ 📁 exceptions
┃ ┃ ┣ 📁 infrastructure
┃ ┃ ┃ ┣ 📁 api
┃ ┃ ┃ ┣ 📁 config
┃ ┃ ┃ ┣ 📁 controllers
┃ ┃ ┃ ┣ 📁 data_sources
┃ ┃ ┃ ┣ 📁 entities
┃ ┃ ┃ ┗ 📁 mappers
┃ ┃ ┣ 📁 interface_adapters
┃ ┃ ┃ ┣ 📁 controllers
┃ ┃ ┃ ┣ 📁 gateways
┃ ┃ ┃ ┗ 📁 presenters
┃ ┗ 📁 resources
┃   ┃ ┣ 📁 db.migration
┃   ┗ 📄 application.yml
┣ 📁 test
┗ 📄 Dockerfile
```

## ⚙️ Funcionalidades

- Cadastro de usuários e seus respectivos endereços
- Autenticação de usuários e alteração de senha
- Definição de papéis de usuário: Cliente ou Dono de Restaurante
- Gerenciamento de restaurantes
- Cadastro e gerenciamento de cardápios
- Cadastro e edição de itens do cardápio

## 🐳 Subindo o Projeto com Docker Compose

1. **Pré-requisitos**:
   - Docker e Docker Compose instalados

2. **Subir o ambiente**:
   -  execute: docker-compose up --build

