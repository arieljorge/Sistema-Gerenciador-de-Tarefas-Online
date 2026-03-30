CREATE TABLE produto_situacao (
    id SMALLSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_externo VARCHAR(50),
    plataforma_origem VARCHAR(50) NOT NULL,
    CONSTRAINT produto_situacao_plataforma_nome_unique UNIQUE (nome, plataforma_origem)
);