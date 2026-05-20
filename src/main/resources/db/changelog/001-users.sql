CREATE TABLE users
(
    id         UUID PRIMARY KEY                       NOT NULL,
    name       VARCHAR(255)                           NOT NULL,
    surname    VARCHAR(255)                           NOT NULL,
    role       VARCHAR(255)                           NOT NULL,
    email      VARCHAR(255)                           NOT NULL UNIQUE,
    password   VARCHAR(255)                           NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);