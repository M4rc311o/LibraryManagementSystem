#!/bin/bash

DB_NAME="library_management_system"
DB_USER="bds-backup"
export PGPASSWORD="piGyTaqRy65VlgML3CQ"

psql -U $DB_USER postgres <<< "DROP DATABASE library_management_system;"
psql -U $DB_USER postgres <<< 'CREATE DATABASE "library_management_system" OWNER "postgres";'
psql -U $DB_USER $DB_NAME < $1
