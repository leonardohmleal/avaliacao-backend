CREATE TABLE if not exists books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL
);
