ALTER TABLE applications ADD COLUMN application_date DATE NULL;

UPDATE applications SET application_date = DATE(created_at);

ALTER TABLE applications
    MODIFY COLUMN application_date DATE NOT NULL DEFAULT (CURRENT_DATE);