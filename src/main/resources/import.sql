DROP TABLE IF EXISTS users;
CREATE TABLE users(id serial PRIMARY KEY, name VARCHAR(100));

INSERT INTO users(name) VALUES('Foo');
INSERT INTO users(name) VALUES('Bar');