CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_name VARCHAR(40) UNIQUE NOT NULL,
    password  VARCHAR(40),
    active    BOOLEAN,
    roles     VARCHAR(40) NOT NULL
);
