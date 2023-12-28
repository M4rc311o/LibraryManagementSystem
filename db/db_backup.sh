#!/bin/bash

DB_NAME="library_management_system"
DB_USER="bds-backup"

BACKUP_DIR="/postgres_backup/"
FILE_NAME="lms-backup-"$(date "+%Y-%m-%d")

pg_dump -U $DB_USER $DB_NAME > $BACKUP_DIR$FILE_NAME".sql.bak" 2>> $BACKUP_DIR$FILE_NAME".bak.log"
