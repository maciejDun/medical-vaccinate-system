CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) UNIQUE NOT NULL,
    roles     VARCHAR(20) NOT NULL
);
