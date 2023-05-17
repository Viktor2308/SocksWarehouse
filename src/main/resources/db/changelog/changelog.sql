-- liquibase formatted sql

-- changeset derkachev:1000000-1
-- comment: Initial creation of table socks
CREATE TABLE "socks"
(
    "id"             UUID           NOT NULL DEFAULT uuid_generate_v1(),
    "color"          VARCHAR(25)    NOT NULL,
    "size"           INTEGER        NOT NULL,
    "cotton_content" INTEGER        NOT NULL,
    "stock"          INTEGER        NOT NULL,
    "price"          numeric(16, 2) NOT NULL,
    "created_at"     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT "socks_pkey" PRIMARY KEY ("id")
);
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
