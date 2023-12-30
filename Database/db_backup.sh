#!/bin/bash

DB_NAME="library_management_system"
DB_USER="bds-backup"
export PGPASSWORD='piGyTaqRy65VlgML3CQ'

BACKUP_DIR="/postgres_backup/"
FILE_NAME="lms-backup-"$(date "+%Y-%m-%d")
FILE_PATH=$BACKUP_DIR$FILE_NAME

pg_dump -U $DB_USER $DB_NAME > $FILE_PATH".sql.bak" 2>> $FILE_PATH".bak.log"
