CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    amount DOUBLE NOT NULL DEFAULT 0.0,
    payment_method VARCHAR(255) NOT NULL,
    terminal_id BIGINT
);