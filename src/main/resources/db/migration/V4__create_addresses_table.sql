create table addresses (
    id BIGINT NOT NULL auto_increment,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    postal_code INTEGER NOT NULL,
    job_opening_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_addresses_job_opening_id
        FOREIGN KEY (job_opening_id)
        REFERENCES job_openings(id)
);