CREATE TABLE products (
                          id UUID PRIMARY KEY,
                          name VARCHAR(150) NOT NULL,
                          description VARCHAR(500),
                          price NUMERIC(12, 2) NOT NULL,
                          stock INTEGER NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL
);