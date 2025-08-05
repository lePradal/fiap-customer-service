-- Criação do tipo ENUM para a categoria
CREATE TYPE category_enum AS ENUM ('SANDWICH', 'SIDE', 'DRINK', 'DESSERT');

-- Criação do tipo ENUM para o status do pedido
CREATE TYPE order_status_enum AS ENUM ('AWAITING_PAYMENT', 'RECEIVED', 'IN_PREPARATION', 'READY', 'FINALIZED');

-- Criação do tipo ENUM para o status do pagamento
CREATE TYPE payments_status_enum AS ENUM ('PENDING', 'PAID');

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS products
(
    id          UUID PRIMARY KEY,
    name        TEXT           NOT NULL,
    price       NUMERIC(10, 2) NOT NULL,
    category    category_enum  NOT NULL,
    description TEXT,
    active      BOOLEAN        NOT NULL DEFAULT TRUE,
    image_url   TEXT
);

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS customers
(
    id    UUID PRIMARY KEY,
    name  TEXT        NOT NULL,
    cpf   VARCHAR(14) NOT NULL UNIQUE,
    email TEXT        NOT NULL UNIQUE,
    phone VARCHAR(20)
);

-- Tabela de pedidos
CREATE TABLE IF NOT EXISTS orders
(
    id          UUID PRIMARY KEY,
    customer_id UUID              NOT NULL REFERENCES customers (id),
    status      order_status_enum NOT NULL DEFAULT 'AWAITING_PAYMENT',
    observation TEXT,
    number      INTEGER           NOT NULL,
    creation_date TIMESTAMP       NOT NULL DEFAULT NOW()
);

-- Tabela de itens do pedido
CREATE TABLE IF NOT EXISTS items
(
    id         UUID PRIMARY KEY,
    order_id   UUID    NOT NULL REFERENCES orders (id) ON DELETE CASCADE,
    product_id UUID    NOT NULL REFERENCES products (id),
    quantity   INTEGER NOT NULL CHECK (quantity > 0)
);

-- Tabela de itens de pagamentos
CREATE TABLE IF NOT EXISTS payments
(
    id         UUID PRIMARY KEY,
    order_id   UUID    NOT NULL REFERENCES orders (id) ON DELETE CASCADE,
    status     payments_status_enum NOT NULL,
    amount     NUMERIC(10, 2) NOT NULL,
    qr_code    TEXT
);
