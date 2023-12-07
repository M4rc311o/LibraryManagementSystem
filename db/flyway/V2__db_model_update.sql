ALTER TABLE bds.access_log
ADD ip inet NOT NULL;

ALTER TABLE bds.address
RENAME COLUMN num_of_house TO house_num;

ALTER TABLE bds.user
ALTER COLUMN password_hash TYPE text;