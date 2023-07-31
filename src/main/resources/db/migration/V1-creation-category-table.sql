CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO category (name) VALUES ('Produtos de Limpeza');
INSERT INTO category (name) VALUES ('Bebidas');
INSERT INTO category (name) VALUES ('Salgadinhos');
INSERT INTO category (name) VALUES ('Chás e Cafés');
INSERT INTO category (name) VALUES ('Grãos e Cereais');
INSERT INTO category (name) VALUES ('Carnes');
INSERT INTO category (name) VALUES ('Doces');