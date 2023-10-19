ALTER TABLE lms.access_log
ADD ip inet NOT NULL;

ALTER TABLE lms.address
RENAME COLUMN num_of_house TO house_num;

ALTER TABLE lms.user
ALTER COLUMN password_hash TYPE text;