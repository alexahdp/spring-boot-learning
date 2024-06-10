--liquibase formatted sql

--changeset aleksandr:1.0
ALTER TABLE users
    ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';
