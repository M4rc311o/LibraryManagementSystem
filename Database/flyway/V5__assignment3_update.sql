CREATE TABLE bds.country (
	country_id bigserial NOT NULL,
	country varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (country_id)
);

INSERT INTO bds.country (country) VALUES ('Illinois');
INSERT INTO bds.country (country) VALUES ('Arizona');
INSERT INTO bds.country (country) VALUES ('Pennsylvania');
INSERT INTO bds.country (country) VALUES ('Massachusetts');
INSERT INTO bds.country (country) VALUES ('Georgia');

ALTER TABLE bds.address
ADD COLUMN country_id bigint,
ADD CONSTRAINT fk_address_has_country
	FOREIGN KEY (country_id)
	REFERENCES bds.country (country_id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;
	
UPDATE bds.address SET country_id = 1 WHERE address_id = 1;
UPDATE bds.address SET country_id = 1 WHERE address_id = 2;
UPDATE bds.address SET country_id = 2 WHERE address_id = 3;
UPDATE bds.address SET country_id = 2 WHERE address_id = 4;
UPDATE bds.address SET country_id = 2 WHERE address_id = 5;
UPDATE bds.address SET country_id = 1 WHERE address_id = 6;
UPDATE bds.address SET country_id = 3 WHERE address_id = 7;
UPDATE bds.address SET country_id = 4 WHERE address_id = 8;
UPDATE bds.address SET country_id = 3 WHERE address_id = 9;
UPDATE bds.address SET country_id = 3 WHERE address_id = 10;
UPDATE bds.address SET country_id = 5 WHERE address_id = 11;
UPDATE bds.address SET country_id = 2 WHERE address_id = 12;
	
ALTER TABLE bds.address
ALTER COLUMN country_id SET NOT NULL;

CREATE TABLE bds.unsecure_staff (
	staff_id bigserial NOT NULL,
	username varchar(45) NOT NULL UNIQUE,
	password varchar(45) NOT NULL,
	join_date date,
	phone_number varchar(45),
	PRIMARY KEY (staff_id)
);

INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('cosmicknight', 'password', '2022-04-11', '+1-597-573-9794');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('digitalecho', '123456', '2020-07-14', '+1-153-569-2100');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('arcticstar', 'qwerty', '2020-04-27', '+1-767-916-6330');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('cosmicdreamer', 'abc123', '2023-03-31', '+1-670-195-3118');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('blueecho', '111111', '2021-09-09', '+1-591-726-4441');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('thunderleap', 'letmein', '2016-04-30', '+1-708-624-2429');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('blueknight', 'monkey', '2014-07-17', '+1-602-467-5776');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('mysticecho', 'dragon', '2023-08-30', '+1-651-249-3097');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('neonfox', 'sunshine', '2023-05-12', '+1-376-209-8656');
INSERT INTO bds.unsecure_staff (username, password, join_date, phone_number) VALUES ('neonwizard', 'iloveyou', '2015-09-02', '+1-604-704-7195');

ALTER TABLE bds.unsecure_staff OWNER TO "bds-app";
