CREATE TABLE bds.genre (
	genre_id bigserial NOT NULL,
	genre varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (genre_id)
);

CREATE TABLE bds.language (
	language_id bigserial NOT NULL,
	language varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (language_id)
);

CREATE TABLE bds.book_binding (
	book_binding_id bigserial NOT NULL,
	book_binding varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (book_binding_id)
);

CREATE TABLE bds.literary_period (
	literary_period_id bigserial NOT NULL,
	literary_period varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (literary_period_id)
);

CREATE TABLE bds.book (
	book_id bigserial NOT NULL,
	ISBN varchar(17) NOT NULL UNIQUE,
	title varchar(45) NOT NULL,
	year_of_publication int NOT NULL,
	evaluation decimal(3, 2),
	genre_id bigint NOT NULL,
	language_id bigint NOT NULL,
	book_binding_id bigint NOT NULL,
	literary_period_id bigint NOT NULL,
	PRIMARY KEY (book_id),
	CONSTRAINT fk_book_has_genre
		FOREIGN KEY (genre_id)
		REFERENCES bds.genre (genre_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_book_has_language
		FOREIGN KEY (language_id)
		REFERENCES bds.language (language_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_book_has_book_binding
		FOREIGN KEY (book_binding_id)
		REFERENCES bds.book_binding (book_binding_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_book_has_literary_period
		FOREIGN KEY (literary_period_id)
		REFERENCES bds.literary_period (literary_period_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.author (
	author_id bigserial NOT NULL,
	first_name varchar(45) NOT NULL,
	last_name varchar(45) NOT NULL,
	date_of_birth date NOT NULL,
	date_of_death date,
	language_id bigint NOT NULL,
	PRIMARY KEY (author_id),
	CONSTRAINT fk_author_has_language
		FOREIGN KEY (language_id)
		REFERENCES bds.language (language_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.book_has_author (
	book_id bigint NOT NULL,
	author_id bigint NOT NULL,
	PRIMARY KEY (book_id, author_id),
	CONSTRAINT fk_book_has_author_book_id
		FOREIGN KEY (book_id)
		REFERENCES bds.book (book_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_book_has_author_author_id
		FOREIGN KEY (author_id)
		REFERENCES bds.author (author_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.city (
	city_id bigserial NOT NULL,
	city varchar(45) NOT NULL UNIQUE,
	PRIMARY KEY (city_id)
);

CREATE TABLE bds.address (
	address_id bigserial NOT NULL,
	street varchar(45) NOT NULL,
	num_of_house int NOT NULL,
	city_id bigint NOT NULL,
	PRIMARY KEY (address_id),
	CONSTRAINT fk_address_has_city
		FOREIGN KEY (city_id)
		REFERENCES bds.city (city_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.library (
	library_id bigserial NOT NULL,
	name varchar(45) NOT NULL,
	address_id bigint NOT NULL,
	PRIMARY KEY (library_id),
	CONSTRAINT fk_library_has_address
		FOREIGN KEY (address_id)
		REFERENCES bds.address (address_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.physical_book (
	physical_book_id bigserial NOT NULL,
	state text NOT NULL DEFAULT 'New',
	book_id bigint NOT NULL,
	library_id bigint,
	PRIMARY KEY (physical_book_id),
	CONSTRAINT fk_physical_book_has_book
		FOREIGN KEY (book_id)
		REFERENCES bds.book (book_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_physical_book_has_library
		FOREIGN KEY (library_id)
		REFERENCES bds.library (library_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.role (
	role_id bigserial NOT NULL,
	role varchar(20),
 	PRIMARY KEY (role_id)
);

CREATE TABLE bds.user (
	user_id bigserial NOT NULL,
	first_name varchar(45) NOT NULL,
	last_name varchar(45) NOT NULL,
	username varchar(45) NOT NULL UNIQUE,
	date_of_birth date,
	password_hash varchar(255) NOT NULL,
	role_id bigint NOT NULL,
	PRIMARY KEY (user_id),
	CONSTRAINT fk_user_has_role
		FOREIGN KEY (role_id)
		REFERENCES bds.role (role_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.user_contact (
	user_contact_id bigserial NOT NULL,
	user_id bigint NOT NULL,
	email varchar(45),
	phone_number varchar(45) NOT NULL,
	PRIMARY KEY (user_contact_id),
	CONSTRAINT fk_contact_has_user
		FOREIGN KEY (user_id)
		REFERENCES bds.user (user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE bds.loaned_book (
	loaned_book_id bigserial NOT NULL,
	user_id bigint,
	physical_book_id bigint NOT NULL,
	is_returned bool NOT NULL DEFAULT FALSE,
	issue_date date NOT	NULL DEFAULT CURRENT_DATE,
	due_date date NOT NULL DEFAULT (CURRENT_DATE + INTERVAL '1 month'),
	library_id bigint NOT NULL,
	PRIMARY KEY (loaned_book_id),
	CONSTRAINT fk_loaned_book_has_user
		FOREIGN KEY (user_id)
		REFERENCES bds.user (user_id)
		ON DELETE SET NULL
		ON UPDATE SET NULL,
	CONSTRAINT fk_loaned_book_has_physical_book
		FOREIGN KEY (physical_book_id)
		REFERENCES bds.physical_book (physical_book_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_loaned_book_has_library
		FOREIGN KEY (library_id)
		REFERENCES bds.library (library_id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);

CREATE TABLE bds.book_request (
	book_request_id bigserial NOT NULL,
	ISBN varchar(17) NOT NULL,
	title varchar(45) NOT NULL,
	user_id bigint NOT NULL,
	PRIMARY KEY (book_request_id),
	CONSTRAINT fk_book_request_has_user
		FOREIGN KEY (user_id)
		REFERENCES bds.user (user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE bds.access_log (
	log_id bigserial NOT NULL,
	"timestamp" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	user_id bigint NOT NULL,
	PRIMARY KEY (log_id),
	CONSTRAINT fk_access_log_has_user
		FOREIGN KEY (user_id)
		REFERENCES bds.user (user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
