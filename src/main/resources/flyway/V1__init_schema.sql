CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_name VARCHAR(255) UNIQUE NOT NULL,
    roles     VARCHAR(20) NOT NULL
);
