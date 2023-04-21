CREATE DATABASE NEW_DATABASE;
GO

USE NEW_DATABASE;
GO

CREATE TABLE Usuario (
   cd_usuario integer identity primary key,
   nome varchar(100),
   nome_social varchar(100)
);

CREATE TABLE Usuario_email(
    cd_email integer identity primary key,
    email varchar(255),
    cd_usuario integer,
    FOREIGN KEY (cd_usuario) REFERENCES Usuario(cd_usuario)
);