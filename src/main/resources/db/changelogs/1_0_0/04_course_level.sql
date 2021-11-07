--liquibase formatted sql

--changeset timurnav:4
ALTER TABLE courses
    ADD COLUMN level VARCHAR NOT NULL DEFAULT 'BASIC';

ALTER TABLE courses
    ALTER COLUMN level DROP DEFAULT;
