CREATE TABLE tenants (

                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                         tenant_code VARCHAR(50) NOT NULL UNIQUE,

                         name VARCHAR(150) NOT NULL,

                         status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                         created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT chk_tenant_status
                             CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED'))

);