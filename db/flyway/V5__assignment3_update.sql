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
