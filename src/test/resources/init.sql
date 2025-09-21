\c postgres;

-- DROP DATABASE IF EXISTS flowcrux_db;
-- CREATE DATABASE flowcrux_db;
-- GRANT ALL PRIVILEGES ON DATABASE flowcrux_db TO mourjo;

\c flowcrux_db;

CREATE TABLE kv_store (
    id SERIAL PRIMARY KEY,
    kv_key TEXT NOT NULL UNIQUE,
    kv_value TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);


