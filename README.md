# API REST - Spring Boot

API REST desenvolvida com **Java e Spring Boot**, utilizando Docker para o banco de dados.

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

| Método | Endpoint | Descrição      |
| ------ | -------- | -------------- |
| POST   | `/jobs`  | Cadastrar vaga |
