CREATE TABLE cart (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    terminal_id BIGINT NOT NULL,
    FOREIGN KEY (terminal_id) REFERENCES terminal(id)
);