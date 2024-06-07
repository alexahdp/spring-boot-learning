--liquibase formatted sql

--changeset aleksandr:1.0
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN created_by VARCHAR(32);

ALTER TABLE users
    ADD COLUMN updated_by VARCHAR(32);
