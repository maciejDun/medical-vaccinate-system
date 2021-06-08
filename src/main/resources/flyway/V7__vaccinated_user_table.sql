CREATE TABLE vaccinated_user
(
    id                  BIGSERIAL PRIMARY KEY,
    user_entity_id      BIGINT UNIQUE NOT NULL,
    term_entity_id BIGINT UNIQUE NOT NULL ,
    FOREIGN KEY (user_entity_id) REFERENCES users (id),
    FOREIGN KEY (term_entity_id) REFERENCES vaccination_term (id)
    );

