# Spring Boot - Debezium

## Prova de conceito

Migrar dados entre databases SQL Server com CDC, utilizando Debezium.

- Debezium

## Requisitos

- Java JDK 17
- Apache Maven 3.8.6
- Docker 20+
- Docker Compose 1.25

## Como executar

1. Rodar `cd docker | docker compose up -d` para subir containers Docker com os databases
2. OLD_DATABASE: Criar um usuário "POC-CDC" com permissão `db_owner`
3. OLD_DATABASE: Habilitar CDC `EXEC sys.sp_cdc_enable_db`
4. OLD_DATABASE: Iniciar SQL Server Agent
5. Executar aplicação `mvn spring-boot:run`

Pronto! Pode inserir, alterar ou deletar registros na tabela OLD_DATABASE.Estudante, deve migrar para as tabelas NEW_DATABASE.usuario e NEW_DATABASE.usuario_email.
