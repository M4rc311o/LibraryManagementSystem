\connect "library_management_system";

-- genre 20
INSERT INTO lms.genre (genre) VALUES ('Mystery');
INSERT INTO lms.genre (genre) VALUES ('Thriller');
INSERT INTO lms.genre (genre) VALUES ('Romance');
INSERT INTO lms.genre (genre) VALUES ('Science Fiction');
INSERT INTO lms.genre (genre) VALUES ('Fantasy');
INSERT INTO lms.genre (genre) VALUES ('Historical Fiction');
INSERT INTO lms.genre (genre) VALUES ('Horror');
INSERT INTO lms.genre (genre) VALUES ('Adventure');
INSERT INTO lms.genre (genre) VALUES ('Non-Fiction');
INSERT INTO lms.genre (genre) VALUES ('Biography');
INSERT INTO lms.genre (genre) VALUES ('Self-Help');
INSERT INTO lms.genre (genre) VALUES ('Young Adult');
INSERT INTO lms.genre (genre) VALUES ('Dystopian');
INSERT INTO lms.genre (genre) VALUES ('Historical Romance');
INSERT INTO lms.genre (genre) VALUES ('Humor');
INSERT INTO lms.genre (genre) VALUES ('Poetry');
INSERT INTO lms.genre (genre) VALUES ('Memoir');
INSERT INTO lms.genre (genre) VALUES ('Cookbook');
INSERT INTO lms.genre (genre) VALUES ('Travel');
INSERT INTO lms.genre (genre) VALUES ('Graphic Novel');

-- language 11
INSERT INTO lms.language (language) VALUES ('English');
INSERT INTO lms.language (language) VALUES ('Spanish');
INSERT INTO lms.language (language) VALUES ('French');
INSERT INTO lms.language (language) VALUES ('German');
INSERT INTO lms.language (language) VALUES ('Russian');
INSERT INTO lms.language (language) VALUES ('Dutch');
INSERT INTO lms.language (language) VALUES ('Greek');
INSERT INTO lms.language (language) VALUES ('Polish');
INSERT INTO lms.language (language) VALUES ('Hebrew');
INSERT INTO lms.language (language) VALUES ('Czech');
INSERT INTO lms.language (language) VALUES ('Slovak');

-- book_binding 2
INSERT INTO lms.book_binding (book_binding) VALUES ('Hardcover');
INSERT INTO lms.book_binding (book_binding) VALUES ('Softcover');

-- literary_period 11
INSERT INTO lms.literary_period (literary_period) VALUES ('Ancient');
INSERT INTO lms.literary_period (literary_period) VALUES ('Medieval');
INSERT INTO lms.literary_period (literary_period) VALUES ('Renaissance');
INSERT INTO lms.literary_period (literary_period) VALUES ('Enlightenment');
INSERT INTO lms.literary_period (literary_period) VALUES ('Romanticism');
INSERT INTO lms.literary_period (literary_period) VALUES ('Victorian');
INSERT INTO lms.literary_period (literary_period) VALUES ('Realism');
INSERT INTO lms.literary_period (literary_period) VALUES ('Naturalism');
INSERT INTO lms.literary_period (literary_period) VALUES ('Modernism');
INSERT INTO lms.literary_period (literary_period) VALUES ('Postmodernism');
INSERT INTO lms.literary_period (literary_period) VALUES ('Contemporary');

-- book
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-580-066-374-1', 'The Enigma Chronicles', 1979, 6.35, 9, 8, 2, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-778-899-989-1', 'Serpents and Shadows', 1993, 2.94, 4, 10, 1, 9);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-227-681-208-8', 'The Clockmaker''s Secret', 1935, 2.73, 7, 10, 1, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-939-780-673-9', 'Songs of the Siren', 1992, 9.69, 7, 11, 2, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-529-730-058-5', 'Harmony of the Cosmos', 1934, 4.86, 13, 9, 1, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-300-488-619-0', 'The Alchemist''s Daughter', 1979, 4.75, 6, 11, 1, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-759-111-029-7', 'Crimson Horizons', 1905, 5.84, 14, 7, 1, 4);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-910-288-819-9', 'The Quantum Paradox', 1933, 9.97, 5, 10, 1, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-486-239-652-0', 'Ghosts of the Machine', 2001, 1.66, 7, 9, 1, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-058-918-502-9', 'Chronicles of the Celestial', 1943, 7.1, 9, 7, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-441-299-183-1', 'Ethereal Echoes', 1940, 2.16, 12, 10, 1, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-590-794-837-9', 'The Illusionist''s Dilemma', 1922, 9.73, 16, 1, 1, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-849-077-987-8', 'Sands of Eternity', 1987, 4.08, 4, 1, 2, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-989-050-839-3', 'The Last Oracle', 1975, 3.33, 2, 3, 1, 8);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-741-835-650-7', 'Inferno of the Mind', 1907, 4.15, 19, 7, 2, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-439-182-102-3', 'The Cursed Kingdom', 1971, 3.57, 2, 2, 2, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-707-136-454-6', 'Stars Beyond the Veil', 1942, 8.78, 8, 5, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-725-108-252-1', 'Beneath the Silver Moon', 1932, 4.63, 14, 10, 1, 5);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-353-636-336-1', 'The Astral Traveler''s Guide', 1996, 5.6, 4, 10, 2, 8);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-482-888-552-0', 'Labyrinth of Souls', 2014, 3.56, 13, 3, 2, 3);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-708-113-555-6', 'Dreams of Another Realm', 1925, 2.82, 8, 1, 2, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-865-087-862-0', 'Echoes from the Abyss', 1927, 6.37, 3, 8, 2, 4);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-696-336-207-6', 'The Enchanted Labyrinth', 1919, 8.81, 7, 2, 2, 5);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-836-826-249-4', 'Requiem for a Star', 2010, 6.12, 8, 11, 1, 3);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-695-553-080-4', 'The Clockwork Constellations', 1944, 0.11, 1, 8, 1, 3);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-731-876-000-4', 'Shadows of the Eternal', 1984, 2.02, 2, 7, 2, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-826-284-282-4', 'Specters of the Past', 1986, 4.29, 17, 3, 1, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-556-185-427-4', 'The Alchemy of Fate', 1969, 0.32, 15, 7, 2, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-459-167-969-3', 'The Oracle''s Prophecy', 1998, 9.2, 1, 11, 2, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-532-055-609-4', 'The Ethereal Nexus', 1998, 9.85, 6, 9, 1, 4);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-968-712-004-2', 'Cogs and Ciphers', 1975, 1.53, 8, 7, 2, 9);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-243-900-388-1', 'The Last Magician', 1955, 8.67, 9, 11, 2, 8);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-647-168-714-1', 'The Quantum Conundrum', 1994, 0.19, 9, 9, 1, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-430-421-952-5', 'Visions of the Void', 1958, 2.87, 15, 3, 2, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-163-232-495-6', 'Sorcery and Stardust', 1964, 3.02, 3, 11, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-686-407-361-4', 'The Enigmatic Empire', 2009, 6.0, 13, 1, 1, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-314-336-359-7', 'Beyond the Veil of Time', 1976, 4.08, 16, 8, 1, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-374-774-194-6', 'The Cosmic Kaleidoscope', 1949, 9.38, 13, 6, 1, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-996-240-705-9', 'The Arcane Archive', 2000, 3.58, 12, 11, 2, 5);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-100-879-997-5', 'The Illusionist''s Mirage', 1988, 0.63, 8, 8, 1, 10);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-572-098-250-6', 'Realm of the Lost Souls', 1976, 7.56, 13, 4, 1, 3);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-629-786-637-9', 'The Celestial Codex', 1925, 0.48, 4, 7, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-096-653-979-4', 'The Alchemist''s Legacy', 2016, 1.65, 8, 1, 2, 5);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-854-444-089-3', 'Chronicles of the Nebula', 1938, 7.21, 18, 11, 2, 7);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-926-356-323-2', 'Eclipse of the Mind', 2012, 6.74, 13, 4, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-483-916-144-9', 'The Astral Alchemist', 1957, 9.2, 8, 10, 2, 2);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-310-487-906-2', 'The Luminous Labyrinth', 1999, 2.13, 15, 4, 1, 3);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-641-844-485-1', 'Spectral Serenity', 1956, 6.19, 11, 7, 2, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-043-402-254-3', 'Harmony of the Spheres', 2008, 7.01, 6, 7, 1, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-788-498-737-6', 'Songs of the Solar Winds', 1921, 4.77, 7, 1, 2, 8);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-025-523-639-0', 'The Quantum Quandary', 1984, 2.19, 7, 5, 2, 11);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-434-327-618-2', 'Ghosts of the Galactic Core', 1924, 0.62, 10, 5, 2, 8);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-492-905-371-3', 'Echoes of Infinity', 1923, 2.28, 10, 1, 1, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-642-908-350-9', 'The Enchanted Echo', 2002, 5.23, 15, 1, 2, 4);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-372-034-639-4', 'The Alchemy of Dreams', 1991, 1.23, 17, 6, 2, 5);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-419-323-203-4', 'Chronicles of the Enigma', 1990, 1.22, 6, 10, 1, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-225-447-550-0', 'Memoirs of a Dreamer', 1985, 6.96, 5, 1, 1, 6);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-902-812-626-8', 'Journey to Self: A Memoir', 1924, 0.69, 18, 9, 2, 1);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-892-189-858-3', 'Whispers of My Soul', 2007, 3.59, 20, 11, 1, 7);
INSERT INTO lms.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) VALUES ('978-708-891-441-7', 'Chasing Dreams: My Life Story', 1948, 7.17, 19, 4, 2, 6);

-- author
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('John', 'Hill', '1918-09-15', '1977-05-16', 4);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('James', 'Hall', '1983-07-25', '2022-02-28', 8);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Ava', 'Brown', '1952-12-07', '2021-11-10', 6);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Benjamin', 'Brown', '1908-05-02', '1975-09-15', 5);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Michael', 'Johnson', '1936-06-12', '1967-06-05', 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Ava', 'Lee', '2001-10-10', null, 11);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('William', 'Adams', '1988-04-01', null, 2);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('William', 'Young', '1941-08-17', '1987-09-19', 9);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('William', 'Clark', '1976-08-21', '2011-12-18', 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Emma', 'Adams', '1993-10-18', null, 7);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Liam', 'Lewis', '1986-09-06', '2020-11-08', 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Liam', 'Johnson', '1952-09-14', '1973-08-20', 6);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Olivia', 'Clark', '1948-01-11', '2012-07-17', 9);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Alice', 'Lee', '1951-05-26', '1993-06-28', 2);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Emma', 'Clark', '1930-12-17', '1969-01-11', 11);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Olivia', 'Hall', '2004-06-21', null, 7);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('William', 'Young', '1966-10-04', null, 7);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Ava', 'Brown', '1992-05-07', null, 8);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Olivia', 'Lee', '2004-05-17', null, 5);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Sophia', 'Taylor', '1986-12-03', null, 5);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Ava', 'Adams', '1912-06-20', '1942-11-06', 11);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Michael', 'Johnson', '1956-02-11', '2023-05-14', 5);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Liam', 'Adams', '1976-06-20', '1999-02-26', 9);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Benjamin', 'Lewis', '1987-06-27', '2003-04-09', 2);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Sophia', 'Lee', '1927-11-17', '1954-07-17', 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Sophia', 'Adams', '1970-06-23', '2002-11-03', 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Alice', 'Clark', '1997-04-06', null, 1);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Emma', 'Hill', '1941-11-07', '1962-09-29', 9);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Alice', 'Smith', '2004-07-03', null, 7);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('James', 'Lewis', '1969-08-15', '1997-03-03', 7);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('James', 'Hall', '1903-02-07', '1933-07-07', 6);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('James', 'Lee', '1956-10-25', '1981-02-25', 3);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Emma', 'Adams', '1941-02-26', '2008-10-22', 2);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Olivia', 'Hall', '1967-04-18', null, 11);
INSERT INTO lms.author (first_name, last_name, date_of_birth, date_of_death, language_id) VALUES ('Emma', 'Lee', '1928-01-23', '1954-02-02', 7);

-- book_has_author

-- city
INSERT INTO lms.city (city) VALUES ('Chicago');
INSERT INTO lms.city (city) VALUES ('Phoenix');
INSERT INTO lms.city (city) VALUES ('Philadelphia');
INSERT INTO lms.city (city) VALUES ('Boston');
INSERT INTO lms.city (city) VALUES ('Atlanta');

-- address
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Maplewood Avenue', 245, 1);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Oakridge Drive', 23, 1);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Greenwood Street', 127, 2);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Magnolia Avenue', 89, 2);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Redwood Drive', 158, 2);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Willow Street', 45, 1);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Sycamore Lane', 39, 3);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Cedar Street', 192, 4);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Rustic Road', 5, 3);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Heritage Lane', 1, 3);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Riverview Avenue', 201, 5);
INSERT INTO lms.address (street, num_of_house, city_id) VALUES ('Goldenrod Street', 230, 2);

-- library
INSERT INTO lms.library (name, address_id) VALUES ('Chapter & Verse', 1);
INSERT INTO lms.library (name, address_id) VALUES ('Knowledge Hub', 2);
INSERT INTO lms.library (name, address_id) VALUES ('Book Nook', 3);
INSERT INTO lms.library (name, address_id) VALUES ('Reading Haven', 4);
INSERT INTO lms.library (name, address_id) VALUES ('The Bookshelf', 5);
INSERT INTO lms.library (name, address_id) VALUES ('Whimsical Words', 6);
INSERT INTO lms.library (name, address_id) VALUES ('Storyteller''s Den', 7);
INSERT INTO lms.library (name, address_id) VALUES ('Page Turner', 8);
INSERT INTO lms.library (name, address_id) VALUES ('Word Weavers', 9);
INSERT INTO lms.library (name, address_id) VALUES ('Literary Corner', 10);
INSERT INTO lms.library (name, address_id) VALUES ('Readers', 11);
INSERT INTO lms.library (name, address_id) VALUES ('Book Oasis', 12);

-- physical_book

-- user_contact
INSERT INTO lms.user_contact (email, phone_number) VALUES ('cool_dude123@example.com', '555-123-7890');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('gamer_girl42@example.org', '444-234-5678');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_ninja_01@example.net', '333-345-6789');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('bookworm_88@example.com', '222-456-7890');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('music_lover99@example.org', '111-567-8901');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('traveler_007@example.net', '666-678-9012');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('foodie_22@example.com', '777-789-0123');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('fitness_guru_123@example.org', '888-890-1234');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('tech_geek@example.net', '999-901-2345');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coffee_addict@example.com', '123-012-3456');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('adventure_seeker@example.org', '234-123-4567');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('daydreamer_42@example.net', '345-234-5678');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('night_owl@example.com', '456-345-6789');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('music_producer@example.org', '567-456-7890');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('globe_trotter@example.net', '678-567-8901');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('hiking_enthusiast@example.com', '789-678-9012');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_guru@example.org', '890-789-0123');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coffee_lover_22@example.net', '901-890-1234');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('star_gazer@example.com', '012-901-2345');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('travel_bug_88@example.org', '123-112-3456');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_whiz@example.net', '234-223-4567');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('food_explorer@example.com', '345-334-5678');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('yoga_master@example.org', '456-445-6789');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('skydiver_007@example.net', '567-556-7890');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('art_lover_123@example.com', '678-667-8901');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('movie_buff@example.org', '789-778-9012');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('gaming_enthusiast@example.net', '890-889-0123');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('travel_junkie@example.com', '901-900-1234');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('fitness_freak@example.org', '012-011-2345');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('nature_lover_22@example.net', '123-122-3456');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('photography_lover@example.com', '234-233-4567');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('wanderlust_88@example.org', '345-344-5678');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('dreamer_42@example.net', '456-455-6789');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('adventure_seeker_007@example.com', '567-566-7890');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_nerd_123@example.org', '678-677-8901');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('travel_enthusiast@example.com', '111-111-2222');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_mastermind@example.org', '222-222-3333');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('gourmet_cook@example.net', '333-333-4444');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('fitness_buff@example.com', '444-444-5555');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('music_maestro@example.org', '555-555-6666');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('book_lover@example.net', '666-666-7777');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('nature_observer@example.com', '777-777-8888');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_whizkid@example.org', '888-888-9999');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('adventure_seeker_22@example.net', '999-999-0000');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coffee_addict_22@example.com', '000-000-1111');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('fitness_guru_22@example.org', '111-111-2222');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('music_lover_22@example.net', '222-222-3333');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('bookworm_22@example.com', '333-333-4444');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('coding_pro_22@example.org', '444-444-5555');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('traveler_22@example.net', '555-555-6666');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('art_enthusiast_22@example.com', '666-666-7777');
INSERT INTO lms.user_contact (email, phone_number) VALUES ('movie_buff_22@example.org', '777-777-8888');


-- role
INSERT INTO lms.role (role) VALUES ('administrator');
INSERT INTO lms.role (role) VALUES ('student');
INSERT INTO lms.role (role) VALUES ('customer');
INSERT INTO lms.role (role) VALUES ('librarian');
INSERT INTO lms.role (role) VALUES ('guest');

-- user
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('John', 'Doe', 'johndoe123', '1990-05-15', lms.crypt('johndoe1231990', lms.gen_salt('bf', 6)), 3, 1);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Alice', 'Smith', 'alicesmith456', '1985-08-23', lms.crypt('alicesmith4561985', lms.gen_salt('bf', 6)), 2, 2);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Michael', 'Johnson', 'mikejohnson789', '1992-02-10', lms.crypt('mikejohnson7891992', lms.gen_salt('bf', 6)), 2, 3);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Emma', 'Williams', 'emmaw123', '1988-11-30', lms.crypt('emmaw1231988', lms.gen_salt('bf', 6)), 3, 4);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('William', 'Brown', 'will.brown', '1995-04-18', lms.crypt('will.brown1995', lms.gen_salt('bf', 6)), 3, 5);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Ethan', 'Davis', 'ethand', '1987-09-12', lms.crypt('ethand1987', lms.gen_salt('bf', 6)), 2, 6);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Olivia', 'Lee', 'olivialee', '1993-03-25', lms.crypt('olivialee1993', lms.gen_salt('bf', 6)), 3, 7);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Liam', 'Anderson', 'liamand', '1986-06-20', lms.crypt('liamand1986', lms.gen_salt('bf', 6)), 2, 8);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Ava', 'Clark', 'avaclark', '1994-01-08', lms.crypt('avaclark1994', lms.gen_salt('bf', 6)), 3, 9);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Mason', 'Walker', 'masonw', '1989-12-02', lms.crypt('masonw1989', lms.gen_salt('bf', 6)), 3, 10);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Harper', 'Evans', 'harper123', '1996-08-14', lms.crypt('harper1231996', lms.gen_salt('bf', 6)), 3, 11);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Logan', 'Turner', 'logant', '1984-10-05', lms.crypt('logant1984', lms.gen_salt('bf', 6)), 3, 12);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Ella', 'Baker', 'ellab', '1997-06-28', lms.crypt('ellab1997', lms.gen_salt('bf', 6)), 3, 13);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Carter', 'Perez', 'carterp', '1983-09-03', lms.crypt('carterp1983', lms.gen_salt('bf', 6)), 3, 14);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Grace', 'Ward', 'graceward', '1998-11-17', lms.crypt('graceward1998', lms.gen_salt('bf', 6)), 2, 15);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Lucas', 'Hayes', 'lucash', '1982-12-09', lms.crypt('lucash1982', lms.gen_salt('bf', 6)), 2, 16);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Lily', 'Fisher', 'lilyf', '1999-04-11', lms.crypt('lilyf1999', lms.gen_salt('bf', 6)), 2, 17);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Landon', 'Ramirez', 'landonr', '1981-02-01', lms.crypt('landonr1981', lms.gen_salt('bf', 6)), 2, 18);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Chloe', 'Barnes', 'chloeb', '2000-07-22', lms.crypt('chloeb2000', lms.gen_salt('bf', 6)), 3, 19);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Alexander', 'Gonzalez', 'alexg', '1980-03-19', lms.crypt('alexg1980', lms.gen_salt('bf', 6)), 2, 20);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Aria', 'Cooper', 'ariac', '2001-10-13', lms.crypt('ariac2001', lms.gen_salt('bf', 6)), 3, 21);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Daniel', 'Gray', 'danielg', '1979-05-07', lms.crypt('danielg1979', lms.gen_salt('bf', 6)), 3, 22);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Jackson', 'Parker', 'jacksonp', '1978-01-16', lms.crypt('jacksonp1978', lms.gen_salt('bf', 6)), 2, 23);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Mia', 'Lopez', 'mial', '2003-12-05', lms.crypt('mial2003', lms.gen_salt('bf', 6)), 3, 24);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Aiden', 'Price', 'aidenp', '1977-06-26', lms.crypt('aidenp1977', lms.gen_salt('bf', 6)), 3, 25);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Scarlett', 'Stewart', 'scarletts', '2004-08-18', lms.crypt('scarletts2004', lms.gen_salt('bf', 6)), 3, 26);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Mason', 'Ross', 'masonr', '1976-11-10', lms.crypt('masonr1976', lms.gen_salt('bf', 6)), 3, 27);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Zoe', 'Ward', 'zoew', '2005-03-03', lms.crypt('zoew2005', lms.gen_salt('bf', 6)), 3, 28);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Elijah', 'Martin', 'elijahm', '1975-07-27', lms.crypt('elijahm1975', lms.gen_salt('bf', 6)), 3, 29);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Avery', 'Fisher', 'averyf', '2006-01-20', lms.crypt('averyf2006', lms.gen_salt('bf', 6)), 3, 30);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Amelia', 'Bryant', 'ameliab', '1974-04-14', lms.crypt('ameliab1974', lms.gen_salt('bf', 6)), 2, 31);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Charlotte', 'Grant', 'charlotteg', '2007-06-07', lms.crypt('charlotteg2007', lms.gen_salt('bf', 6)), 3, 32);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Noah', 'Simmons', 'noahs', '1973-09-30', lms.crypt('noahs1973', lms.gen_salt('bf', 6)), 3, 33);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Liam', 'Butler', 'liamb', '2008-11-23', lms.crypt('liamb2008', lms.gen_salt('bf', 6)), 2, 34);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Grace', 'Turner', 'gracet', '1972-02-15', lms.crypt('gracet1972', lms.gen_salt('bf', 6)), 3, 35);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Emma', 'Mitchell', 'emmam', '2009-05-10', lms.crypt('emmam2009', lms.gen_salt('bf', 6)), 2, 36);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Lucas', 'Griffin', 'lucasg', '1971-08-04', lms.crypt('lucasg1971', lms.gen_salt('bf', 6)), 2, 37);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Olivia', 'Foster', 'oliviaf', '2010-10-29', lms.crypt('oliviaf2010', lms.gen_salt('bf', 6)), 3, 38);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Ethan', 'Ward', 'ethanw', '1970-01-22', lms.crypt('ethanw1970', lms.gen_salt('bf', 6)), 3, 39);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Sophia', 'Lynch', 'sophial', '2011-04-17', lms.crypt('sophial2011', lms.gen_salt('bf', 6)), 3, 40);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Lily', 'Roberts', 'lilyr', '2012-06-12', lms.crypt('lilyr2012', lms.gen_salt('bf', 6)), 3, 41);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Aiden', 'Larson', 'aidenl', '1969-09-05', lms.crypt('aidenl1969', lms.gen_salt('bf', 6)), 2, 42);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Zoe', 'Harrison', 'zoeh', '2013-12-28', lms.crypt('zoeh2013', lms.gen_salt('bf', 6)), 3, 43);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Elijah', 'Thompson', 'elijaht', '1968-03-21', lms.crypt('elijaht1968', lms.gen_salt('bf', 6)), 3, 44);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Ava', 'Phillips', 'avap', '2014-07-14', lms.crypt('avap2014', lms.gen_salt('bf', 6)), 3, 45);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Jackson', 'Morgan', 'jackm', '2015-09-17', lms.crypt('jackm2015', lms.gen_salt('bf', 6)), 2, 46);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Sofia', 'Rogers', 'sofiar', '1967-12-10', lms.crypt('sofiar1967', lms.gen_salt('bf', 6)), 3, 47);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Lucas', 'Adams', 'lucasa', '2016-03-05', lms.crypt('lucasa2016', lms.gen_salt('bf', 6)), 2, 48);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Mia', 'Carter', 'miac', '1966-06-28', lms.crypt('miac1966', lms.gen_salt('bf', 6)), 3, 49);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Liam', 'Stewart', 'liams', '2017-10-21', lms.crypt('liams2017', lms.gen_salt('bf', 6)), 2, 50);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Sophia', 'Miller', 'sophiam', '1991-07-07', lms.crypt('sophiam1991', lms.gen_salt('bf', 6)), 3, 51);
INSERT INTO lms.user (first_name, last_name, username, date_of_birth, password_hash, role_id, user_contact_id) VALUES ('Sofia', 'Hill', 'sofiah', '2002-09-29', lms.crypt('sofiah2002', lms.gen_salt('bf', 6)), 3, 52);

-- loaned_book

-- book_request

-- access_log
