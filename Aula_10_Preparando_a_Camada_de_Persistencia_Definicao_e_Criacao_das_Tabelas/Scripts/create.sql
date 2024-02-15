/* Criando Database */
CREATE DATABASE
        estacionamento;


/* Usando Database */
USE
        estacionamento;


/* Criando Tabelas */
CREATE TABLE TB_MOVIMENTACAO (
        id INT NOT NULL AUTO_INCREMENT
      , placa VARCHAR(8) NOT NULL
      , marca VARCHAR(45)
      , modelo VARCHAR(45)
      , cor VARCHAR(50)
      , data_entrada DATETIME NOT NULL
      , data_saida DATETIME
      , valor DECIMAL(8,2)
      , PRIMARY KEY (id)
);


CREATE TABLE TB_STATUS_VAGA (
        id INT NOT NULL AUTO_INCREMENT
      , ocupadas INT NOT NULL
      , PRIMARY KEY (id)
);

/* Inserindo Dados */
INSERT INTO TB_STATUS_VAGA (ocupadas) VALUES (0);

/* Verificando Dados */
SELECT 
    *
FROM
    TB_STATUS_VAGA;
