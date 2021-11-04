--liquibase formatted sql

--changeset timurnav:2
ALTER TABLE USERS
    ADD COLUMN password VARCHAR NOT NULL DEFAULT '0000',
    ADD COLUMN banned   BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE users
    ALTER COLUMN password DROP DEFAULT;

UPDATE contacts
SET email = ''
WHERE email IS NULL;

ALTER TABLE contacts
    ALTER COLUMN email SET NOT NULL;
