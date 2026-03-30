CREATE TABLE cliente (
    id SMALLSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    db_host VARCHAR(100),
    db_username VARCHAR(50) NOT NULL,
    db_password VARCHAR(50) NOT NULL
);