INSERT INTO author (first_name, last_name, version) VALUES ('Joshua', 'Bloch', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Gavin', 'King', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Christian', 'Bauer', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Gary', 'Gregory', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Raoul-Gabriel', 'Urma', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Mario', 'Fusco', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Alan', 'Mycroft', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Andrew Lee', 'Rubinger', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Aslak', 'Knutsen', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Bill', 'Burke', 0);
INSERT INTO author (first_name, last_name, version) VALUES ('Scott', 'Oaks', 0);

INSERT INTO publisher (name, version) VALUES ('Addison-Wesley Professional',  0);
INSERT INTO publisher (name, version) VALUES ('Manning Publications',  0);
INSERT INTO publisher (name, version) VALUES ('OReilly Media, Inc',  0);

INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2008-05-08', 'Effective Java', 0, 1);
INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2015-10-01', 'Java Persistence with Hibernate', 0, 2);
INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2014-08-28', 'Java 8 in Action', 0, 2);
INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2014-03-12', 'Continuous Enterprise Development in Java', 0, 3);
INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2010-09-08', 'Enterprise JavaBeans 3.1', 0, 3);
INSERT INTO book (publishing_date, title, version, publisher_id) VALUES ('2014-04-29', 'Java Performance The Definitive Guide', 0, 3);

INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
INSERT INTO book_author (book_id, author_id) VALUES (2, 3);
INSERT INTO book_author (book_id, author_id) VALUES (2, 4);
INSERT INTO book_author (book_id, author_id) VALUES (3, 5);
INSERT INTO book_author (book_id, author_id) VALUES (3, 6);
INSERT INTO book_author (book_id, author_id) VALUES (3, 7);
INSERT INTO book_author (book_id, author_id) VALUES (4, 8);
INSERT INTO book_author (book_id, author_id) VALUES (4, 9);
INSERT INTO book_author (book_id, author_id) VALUES (5, 8);
INSERT INTO book_author (book_id, author_id) VALUES (5, 10);
INSERT INTO book_author (book_id, author_id) VALUES (6, 11);