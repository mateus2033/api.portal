create table enterprises (
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    logo BLOB,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_enterprises_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);