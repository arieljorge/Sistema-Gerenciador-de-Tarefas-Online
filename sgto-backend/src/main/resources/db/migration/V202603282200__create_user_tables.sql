CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha_hash TEXT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    atualizado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX idx_users_email ON usuarios(email);
CREATE UNIQUE INDEX idx_users_username ON usuarios(username);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL UNIQUE,
    descricao TEXT
);

INSERT INTO roles(nome) VALUES ('ROLE_USUARIO');
INSERT INTO roles(nome) VALUES ('ROLE_ADMIN');

CREATE TABLE usuarios_roles (
    id_usuario UUID NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    id_role INT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (id_usuario, id_role)
);