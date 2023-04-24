# spring-boot-debezium

# Steps
1. Subir infra `cd docker | docker compose up -d`
2. Criar um usuário com permissões `db_owner`
3. Habilitar CDC `EXEC sys.sp_cdc_enable_db`
4. Criar filegroup 
5. Iniciar SQL Server Agent