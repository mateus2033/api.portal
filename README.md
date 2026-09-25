# API REST - Spring Boot

API REST desenvolvida com **Java e Spring Boot**, com o objetivo de conectar candidatos e empresas de forma simples, oferecendo uma base para cadastro de empresas, publicação de vagas e autenticação de usuários.

## Tecnologias

* Java
* Spring Boot
* Spring Security
* JPA / Hibernate
* Docker
* Banco de dados SQL

## Instalação

Execute o comando:

```bash
docker compose up --build
```

## Funcionalidades

* **Autenticação de usuários** — cadastro e login com Spring Security
* **Gestão de empresas** — cadastro e atualização de dados de empresas
* **Gestão de vagas** — publicação e busca de vagas
* **Candidaturas** — candidatura a vagas e consulta de candidaturas do usuário

## Endpoints

### Autenticação

| Método | Endpoint           | Descrição           |
| ------ | ------------------ | ------------------- |
| POST   | `/sign-in/sign-in` | Login               |
| POST   | `/sign-in/sign-up` | Cadastro de usuário |

### Empresas

| Método | Endpoint      | Descrição         |
| ------ | ------------- | ----------------- |
| POST   | `/enterprise` | Cadastrar empresa |
| PUT    | `/enterprise` | Atualizar empresa |

### Vagas

| Método | Endpoint | Descrição                              |
| ------ | -------- | --------------------------------------- |
| POST   | `/jobs`  | Cadastrar vaga                          |
| GET    | `/jobs`  | Listar vagas (busca, paginação e ordenação) |

### Candidaturas

| Método | Endpoint                      | Descrição                                   |
| ------ | ------------------------------ | -------------------------------------------- |
| POST   | `/user/applicatejob`          | Candidatar-se a uma vaga                     |
| GET    | `/application/myapplications` | Listar candidaturas do usuário (com paginação) |

## Documentação da API

Documentação interativa (Swagger) em desenvolvimento.
