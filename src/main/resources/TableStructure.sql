drop database if exists pencilbook_db;
CREATE database pencilbook_db default character set utf8;
USE db_pencilbook;

CREATE TABLE User(
	id INTEGER AUTO_INCREMENT ,
    username VARCHAR(100),
    pass VARCHAR(100),
    email VARCHAR(200),
    level INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE Post(
	id INTEGER AUTO_INCREMENT ,
    userId INTEGER,
    postText VARCHAR(300),
    picUrl TEXT,
	title VARCHAR(300),
    postDate DATETIME DEFAULT CURRENT_TIMESTAMP(),
    location VARCHAR(200),
	isActive VARCHAR(10) DEFAULT "true",
	PRIMARY KEY (id)
);

CREATE TABLE Tag(
	id INTEGER AUTO_INCREMENT ,
    tagName VARCHAR(200),
    postId INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE Pleasant(
	id INTEGER AUTO_INCREMENT ,
	userId INTEGER ,
	postId INTEGER ,
	likeDate DATETIME DEFAULT CURRENT_TIMESTAMP(),
	PRIMARY KEY (id)
);

CREATE TABLE Comment(
	id INTEGER AUTO_INCREMENT ,
	userId INTEGER ,
	postId INTEGER ,
    addDate DATETIME,
    commentText VARCHAR(300),
	PRIMARY KEY (id)
);

alter table Post add FOREIGN KEY (userId) REFERENCES User(id) ON UPDATE CASCADE;
alter table Tag add FOREIGN KEY (postId) REFERENCES Post(id) ON UPDATE CASCADE;

alter table Pleasant add FOREIGN KEY (userId) REFERENCES User(id) ON UPDATE CASCADE;
alter table Pleasant add FOREIGN KEY (postId) REFERENCES Post(id) ON UPDATE CASCADE;

alter table Comment add FOREIGN KEY (userId) REFERENCES User(id) ON UPDATE CASCADE;
alter table Comment add FOREIGN KEY (postId) REFERENCES Post(id) ON UPDATE CASCADE;

