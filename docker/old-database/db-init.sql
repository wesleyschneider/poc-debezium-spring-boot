CREATE DATABASE OLD_DATABASE;
GO

USE OLD_DATABASE;
GO

EXEC sys.sp_cdc_enable_db
GO

-- CREATE TABLE Estudante (
--     cd_estudante integer identity primary key,
--     nome varchar(100),
--     nome_social varchar(100),
--     email varchar(255)
-- );
-- GO

-- INSERT INTO Estudante VALUES ('Wesley Schneider', 'Wesley', 'wesley@email.com');
-- INSERT INTO Estudante VALUES ('Wesley Schneider 2', 'Wesley 2', 'wesley2@email.com');
-- GO