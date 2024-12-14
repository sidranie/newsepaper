INSERT INTO person (identifier, email, password, given_name, family_name, is_publisher)
VALUES ('test', 'test@test.test', 'test', 'te', 'st', 0);

INSERT INTO person (identifier, email, password, given_name, family_name, is_publisher)
VALUES ('publishperson', 'publish@person.one', 'publishperson', 'publish', 'person', 1);

INSERT INTO person (identifier, email, password, given_name, family_name, is_publisher)
VALUES ('admin', 'admin@newsepaper.fr', 'admin', 'admin', 'admin', 1);

INSERT INTO newsletter(headline, abstract, publisher_id)
VALUES ('The second newsletter', 'The newsletter of the second person', 2);

INSERT INTO news (headline, news_body, newsletter_id, created_at)
VALUES ('Hello', 'This is the first news', 1, '2024-11-01T20:00:00');

INSERT INTO subscription (id, person_id, newsletter_id, since)
VALUES (1, 1, 1, '2024-01-01T00:00:00Z');
