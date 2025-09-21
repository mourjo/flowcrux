\c postgres;

-- DROP DATABASE IF EXISTS flowcrux_db;
-- CREATE DATABASE flowcrux_db;
-- GRANT ALL PRIVILEGES ON DATABASE flowcrux_db TO mourjo;

\c flowcrux_db;

CREATE TABLE kv_store (
    id SERIAL PRIMARY KEY,
    kv_key TEXT NOT NULL UNIQUE,
    kv_value TEXT,
    version INT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);


CREATE TABLE kv_store_history (
    id SERIAL PRIMARY KEY,
    kv_key TEXT NOT NULL,
    kv_value TEXT,
    key_version INT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE kv_store_translations (
    id SERIAL PRIMARY KEY,
    kv_key TEXT NOT NULL,
    lang TEXT NOT NULL,
    translation TEXT NOT NULL,
    key_version INT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);