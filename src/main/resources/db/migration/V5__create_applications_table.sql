create table applications (
    id BIGINT NOT NULL auto_increment,
    curriculum MEDIUMBLOB  NOT NULL,
    job_opening_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),

    CONSTRAINT fk_applications_job_opening_id
        FOREIGN KEY (job_opening_id)
        REFERENCES job_openings(id),

    CONSTRAINT fk_applications_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);