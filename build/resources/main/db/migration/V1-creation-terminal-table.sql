CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    cart_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (cart_id) REFERENCES cart(id)
 );