--liquibase formatted sql

--changeset aleksandr:1.0
ALTER TABLE users
    ADD COLUMN image VARCHAR(255);
