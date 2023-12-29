CREATE ROLE "bds-app" NOSUPERUSER LOGIN ENCRYPTED PASSWORD 'biDyTaYRy66VtgYL3EE';
CREATE ROLE "bds-backup" SUPERUSER LOGIN ENCRYPTED PASSWORD 'piGyTaqRy65VlgML3CQ';
CREATE ROLE "bds-flyway" SUPERUSER LOGIN ENCRYPTED PASSWORD 'gjRsWsdLj35VlgDF5JL';
CREATE DATABASE "library_management_system" OWNER "postgres";
GRANT CONNECT ON DATABASE "library_management_system" TO "bds-app";
REVOKE CREATE ON SCHEMA public FROM PUBLIC;
\connect "library_management_system";
CREATE SCHEMA IF NOT EXISTS "bds" AUTHORIZATION "postgres";
CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA "bds";
DROP SCHEMA IF EXISTS "public" CASCADE;
GRANT USAGE ON SCHEMA "bds" TO "bds-app";
ALTER DEFAULT PRIVILEGES FOR ROLE "bds-flyway" IN SCHEMA "bds" GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "bds-app";
ALTER DEFAULT PRIVILEGES FOR ROLE "bds-flyway" IN SCHEMA "bds" GRANT ALL PRIVILEGES ON SEQUENCES TO "bds-app";
ALTER DEFAULT PRIVILEGES FOR ROLE "bds-backup" IN SCHEMA "bds" GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "bds-app";
ALTER DEFAULT PRIVILEGES FOR ROLE "bds-backup" IN SCHEMA "bds" GRANT ALL PRIVILEGES ON SEQUENCES TO "bds-app";