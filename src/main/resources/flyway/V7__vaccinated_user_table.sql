CREATE TABLE vaccinated_user
(
    id                  BIGSERIAL PRIMARY KEY,
    user_entity_id      UUID UNIQUE NOT NULL,
    vaccination_term_id BIGINT UNIQUE NOT NULL ,
    FOREIGN KEY (user_entity_id) REFERENCES users (id),
    FOREIGN KEY (vaccination_term_id) REFERENCES vaccination_term (id)
    );
