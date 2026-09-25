create table job_openings (
    id BIGINT NOT NULL auto_increment,
    code varchar(50) UNIQUE NOT NULL,
    name varchar(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    level VARCHAR(50) NOT NULL,
    application_limit INT UNSIGNED NOT NULL,
    publication_date DATE NOT NULL,
    due_date DATE NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT NOT NULL,
    enterprise_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_jobs_openings_enterprise_id
        FOREIGN KEY (enterprise_id)
        REFERENCES enterprises(id)
);

CREATE INDEX idx_job_openings_active_due_date ON job_openings (active, due_date);
CREATE FULLTEXT INDEX search_job_opening ON job_openings (name);