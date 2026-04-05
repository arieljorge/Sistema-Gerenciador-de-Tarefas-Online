CREATE TABLE quadro (
    id SMALLSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE tarefa (
    id SERIAL PRIMARY KEY,
    id_quadro SMALLINT NOT NULL,
    titulo VARCHAR(120) NOT NULL,
    descricao TEXT,
    criado_em TIMESTAMP DEFAULT now(),
    prazo TIMESTAMP,
    FOREIGN KEY (id_quadro) REFERENCES quadro (id) ON DELETE CASCADE
);

CREATE TABLE tarefa_usuarios (
    id_tarefa INTEGER NOT NULL REFERENCES tarefa(id) ON DELETE CASCADE,
    id_usuario UUID NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    PRIMARY KEY (id_tarefa, id_usuario)
);