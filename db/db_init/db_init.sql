CREATE ROLE "bds-app" NOSUPERUSER LOGIN ENCRYPTED PASSWORD 'biDyTaYRy66VtgYL3EE';
CREATE DATABASE "library_management_system" OWNER "postgres";
GRANT CONNECT ON DATABASE "library_management_system" TO "bds-app";
REVOKE CREATE ON SCHEMA public FROM PUBLIC;
\connect "library_management_system";
CREATE SCHEMA IF NOT EXISTS "bds" AUTHORIZATION "bds-app";
DROP SCHEMA IF EXISTS "public" CASCADE;
GRANT USAGE ON SCHEMA "bds" TO "bds-app";
ALTER DEFAULT PRIVILEGES IN SCHEMA "bds" GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "bds-app";
ALTER DEFAULT PRIVILEGES IN SCHEMA "bds" GRANT ALL PRIVILEGES ON SEQUENCES TO "bds-app";

ALTER SYSTEM SET log_destination = 'stderr';
ALTER SYSTEM SET logging_collector = on;
ALTER SYSTEM SET log_filename = 'postgresql-%Y-%m-%d_%H%M%S.log';
ALTER SYSTEM SET log_file_mode = '0640';
ALTER SYSTEM SET log_truncate_on_rotation = off;
ALTER SYSTEM SET log_min_messages = warning;
ALTER SYSTEM SET log_min_error_statement = error;
ALTER SYSTEM SET log_connections = on;
ALTER SYSTEM SET log_disconnections = on;
ALTER SYSTEM SET log_statement = ddl;

SELECT pg_reload_conf();
