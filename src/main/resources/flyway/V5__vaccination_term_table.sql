CREATE TABLE vaccination_term
(
    id                      BIGSERIAL PRIMARY KEY,
    creation_date           TIMESTAMP NOT NULL DEFAULT current_timestamp,
    vaccination_date        TIMESTAMP NOT NULL,
    vaccination_facility_id BIGINT    NOT NULL,
    FOREIGN KEY (vaccination_facility_id) REFERENCES vaccination_facility (id)
);
