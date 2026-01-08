-- Tabela de clientes
CREATE TABLE IF NOT EXISTS customers
(
    id    UUID PRIMARY KEY,
    name  TEXT        NOT NULL,
    cpf   VARCHAR(14) NOT NULL UNIQUE,
    email TEXT        NOT NULL UNIQUE,
    phone VARCHAR(20)
);