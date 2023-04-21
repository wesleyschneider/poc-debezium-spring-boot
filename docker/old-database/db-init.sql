CREATE DATABASE OLD_DATABASE;
GO

USE OLD_DATABASE;
GO

CREATE TABLE Estudante (
    cd_estudante integer identity primary key,
    nome varchar(100),
    nome_social varchar(100),
    email varchar(255)
);

INSERT INTO Estudante VALUES ('Wesley Schneider', 'Wesley', 'wesley@email.com');
INSERT INTO Estudante VALUES ('Wesley Schneider 2', 'Wesley 2', 'wesley2@email.com');