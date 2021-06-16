CREATE TABLE users_role
(
    users_id      BIGINT NOT NULL,
    role_entity_id BIGINT NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (role_entity_id) REFERENCES role_entity (id)
);

