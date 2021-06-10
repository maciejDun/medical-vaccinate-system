CREATE TABLE vaccination_facility
(
    id      BIGSERIAL PRIMARY KEY,
    city    VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    state   VARCHAR(255)  NOT NULL,
    address   VARCHAR(255)  NOT NULL
);
