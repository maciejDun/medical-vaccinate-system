CREATE TABLE vaccination_term
(
    id                 BIGSERIAL PRIMARY KEY,
    creation_date      TIMESTAMP NOT NULL DEFAULT current_timestamp,
    vaccination_date   TIMESTAMP NOT NULL,
    facility_entity_id BIGINT    NOT NULL,
    FOREIGN KEY (facility_entity_id) REFERENCES vaccination_facility (id),
    UNIQUE (vaccination_date, facility_entity_id)
);
