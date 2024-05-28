CREATE TABLE authorities
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    deleted BIT(1) NOT NULL,
    name    VARCHAR(255) NULL,
    CONSTRAINT pk_authorities PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    deleted         BIT(1) NOT NULL,
    name            VARCHAR(255) NULL,
    user_name       VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    hashed_password VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_authorities
(
    user_id        BIGINT NOT NULL,
    authorities_id BIGINT NOT NULL
);

ALTER TABLE user_authorities
    ADD CONSTRAINT fk_useaut_on_authorities FOREIGN KEY (authorities_id) REFERENCES authorities (id);

ALTER TABLE user_authorities
    ADD CONSTRAINT fk_useaut_on_user FOREIGN KEY (user_id) REFERENCES user (id);