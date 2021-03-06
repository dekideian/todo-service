CREATE SCHEMA IF NOT EXISTS c4c_connector;
--
CREATE TABLE IF NOT EXISTS c4c_connector.c4c_tenant_metadata (
    tenant_id UUID NOT NULL,
    c4c_account_id VARCHAR NOT NULL,
    c4c_client_id VARCHAR NOT NULL,
    c4c_client_secret VARCHAR NOT NULL,
    c4c_tenant_url VARCHAR NOT NULL,
    created_at DATE,
    CONSTRAINT c4c_tenant_metadata_pkey PRIMARY KEY (tenant_id)
);

    