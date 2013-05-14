# Feeds schema
 
# --- !Ups


CREATE TABLE feeds(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    URL varchar(255),
	title varchar(30)
);

CREATE TABLE persons(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(30),
	email VARCHAR(50),
	password VARCHAR(10),
	subject1 VARCHAR(100), 
	subject2 VARCHAR(100), 
	subject3 VARCHAR(100)
);

CREATE TABLE topics(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(30)
);

CREATE TABLE trends(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description VARCHAR(50) NOT NULL, 
    date DATE NOT NULL,
	importance DOUBLE NOT NULL
	);
	
CREATE  TABLE personFollowsTrend(
	person INTEGER NOT NULL REFERENCES persons(id),
    trend INTEGER NOT NULL REFERENCES trends(id), 
	PRIMARY KEY (person,trend));
	
CREATE TABLE personLikesTopic(
	person INTEGER NOT NULL REFERENCES persons(id),
    topic INTEGER NOT NULL REFERENCES topics(id),
	PRIMARY KEY(person, topic));
	
CREATE TABLE personFollowsFeed(
	person INTEGER NOT NULL REFERENCES persons(id)
    feed INTEGER NOT NULL REFERENCES feeds(id),
	titel VARCHAR(30),
	PRIMARY KEY(person, feed));
	
	
# --- !Downs
 
DROP TABLE feeds;
DROP TABLE persons;
DROP TABLE topics;
DROP TABLE trends;
DROP TABLE personFollowsFeed;
DROP TABLE personLikesTopic;
DROP TABLE personFollowsTrend;
