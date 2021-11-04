--liquibase formatted sql

--changeset timurnav:3
ALTER TABLE USERS
    ADD COLUMN version INTEGER;
