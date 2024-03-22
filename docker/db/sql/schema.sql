CREATE DATABASE IF NOT EXISTS `roadmap_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `roadmap_project`;

-- Schema DDL

CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    enabled  BOOLEAN     NOT NULL
);
CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username),
    UNIQUE INDEX ix_auth_username (username, authority)
);

CREATE TABLE IF NOT EXISTS roadmap
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    username     VARCHAR(50) NOT NULL,
    roadmap_name VARCHAR(12)
);
CREATE TABLE IF NOT EXISTS roadmap_resources
(
    id                   VARCHAR(36) NOT NULL PRIMARY KEY,
    roadmap_id           VARCHAR(36) NOT NULL,
    revision_resource_id VARCHAR(36) NOT NULL
);
CREATE TABLE IF NOT EXISTS revision_resource
(
    id                 VARCHAR(36)  NOT NULL PRIMARY KEY,
    topic_id           VARCHAR(36)  NOT NULL,
    resource_name      VARCHAR(20)  NOT NULL,
    description        VARCHAR(100) NOT NULL,
    where_to_access    VARCHAR(100) NOT NULL,
    level_of_expertise varchar(12)  NOT NULL
);

CREATE TABLE IF NOT EXISTS topics
(
    id         VARCHAR(36) NOT NULL PRIMARY KEY,
    subject_id VARCHAR(36),
    topic_name VARCHAR(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS subjects
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    subject_name VARCHAR(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS user_topics
(
    username           VARCHAR(50) NOT NULL,
    roadmap_id         VARCHAR(36) not null,
    topic_id           VARCHAR(36) NOT NULL,
    level_of_expertise VARCHAR(12) NOT NULL,
    PRIMARY KEY (username, roadmap_id, topic_id)
);

INSERT INTO users (username, password, enabled)
VALUES ('renas', 'renas', true),
       ('admin', 'admin', true),
       ('renasTwo', 'renas', true);

INSERT INTO authorities (username, authority)
VALUES ('renas', 'ROLE_USER'),
       ('admin', 'ROLE_USER'),
       ('admin', 'ROLE_ADMIN'),
       ('renasTwo', 'ROLE_USER');

INSERT INTO roadmap (id, username, roadmap_name)
VALUES ('8894517b-539a-4b89-b0a0-849e84329181', 'renas', 'name1');

INSERT INTO revision_resource (id, topic_id, resource_name, description, where_to_access, level_of_expertise)
VALUES ( 'c7ad9682-f26d-4124-a7e3-75040b511cce', '22be771a-7803-445f-b88f-732fd6170f56', 'Video'
       , 'algebra for newbies', 'www.algebra.com', 'NOVICE'),
       ( 'fa4c2f8f-0979-403f-8f4f-fc8f97d90d24', '22be771a-7803-445f-b88f-732fd6170f56', 'Video'
       , 'algebra for intermediates', 'www.algebra.com', 'INTERMEDIATE'),
       ( '982514fb-0829-40db-84bf-095c337f4b29', '22be771a-7803-445f-b88f-732fd6170f56', 'Video'
       , 'algebra for advanced', 'www.algebra.com', 'EXPERT'),
       ( '9d2e4f26-f3a0-462b-b143-1ff7663f1892', '1915b4be-7f11-48bb-97ff-88f9297104f8', 'Website'
       , 'calculus for newbies', 'www.calculus.com', 'NOVICE'),
       ( '5f0d9ebe-3ecb-4a49-b240-c1fd2dfd9cd6', '1915b4be-7f11-48bb-97ff-88f9297104f8', 'Video'
       , 'calculus for intermediates', 'www.calculus.com', 'INTERMEDIATE'),
       ( 'ea606b91-2f26-4961-a96c-6494587e7a7a  ', '1915b4be-7f11-48bb-97ff-88f9297104f8', 'Website'
       , 'calculus for advanced', 'www.calculus.com', 'EXPERT'),
       ( '84fcf959-7a7d-4a40-96cd-7a2266f0f754', 'af9253a5-9c4f-4095-9778-fadd3a288935', 'Website'
       , 'addition for newbies', 'www.addition.com', 'NOVICE'),
       ( '34fd2bd7-a620-49c7-bfef-d77f7148b83c', 'af9253a5-9c4f-4095-9778-fadd3a288935', 'Video'
       , 'addition for intermediates', 'www.addition.com', 'INTERMEDIATE'),
       ( 'd263c583-2f21-4b5e-95f9-7b42de884345', 'af9253a5-9c4f-4095-9778-fadd3a288935', 'Website'
       , 'addition for advanced', 'www.addition.com', 'EXPERT'),
       ( 'b1f17302-2982-45be-b7b3-443c0bec03dd', '24fb37ab-18ed-42c7-9bc3-09b750aca27a', 'Website'
       , 'englishLit for newbies', 'www.englishLit.com', 'NOVICE'),
       ( '99a5331f-d794-4c51-8232-73309c8d8734', '24fb37ab-18ed-42c7-9bc3-09b750aca27a', 'Video'
       , 'englishLit for intermediates', 'www.englishLit.com', 'INTERMEDIATE'),
       ( '877a2b3e-3f92-47bb-8932-bc57938b40ab', '24fb37ab-18ed-42c7-9bc3-09b750aca27a', 'Website'
       , 'englishLit for advanced', 'www.englishLit.com', 'EXPERT'),
       ( 'f564a778-171a-451d-a316-ce68746c4d75', 'c431c80b-31c6-41c2-99e2-ab6690d4da85', 'Website'
       , 'EnglishLanguage for newbies', 'www.EnglishLanguage.com', 'NOVICE'),
       ( '4491bd84-e282-49bb-ae49-418f6c5b38bb', 'c431c80b-31c6-41c2-99e2-ab6690d4da85', 'Video'
       , 'EnglishLanguage for intermediates', 'www.EnglishLanguage.com', 'INTERMEDIATE'),
       ( 'd06452c2-37ce-4c52-a9ad-64febe043bc1', 'c431c80b-31c6-41c2-99e2-ab6690d4da85', 'Website'
       , 'EnglishLanguage for advanced', 'www.EnglishLanguage.com', 'EXPERT'),
       ( '43b5c369-f368-4c22-9ab7-eeaaabcaada8', '79129a45-bc7e-4bdf-9a09-8421609ebdc8', 'Website'
       , 'CPU for newbies', 'www.CPU.com', 'NOVICE'),
       ( '8181f6f0-d300-48e7-a214-0485c860f892', '79129a45-bc7e-4bdf-9a09-8421609ebdc8', 'Video'
       , 'CPU for intermediates', 'www.CPU.com', 'INTERMEDIATE'),
       ( '428598f2-bea3-4ac0-b948-a3d9cf2c9544', '79129a45-bc7e-4bdf-9a09-8421609ebdc8', 'Website'
       , 'CPU for advanced', 'www.CPU.com', 'EXPERT'),
       ( 'deaf35b4-4f56-43b5-8731-f988d62b4458', '4fdb1e44-6d6b-43cc-9c08-7c0cd0ffca15', 'Website'
       , 'java for newbies', 'www.java.com', 'NOVICE'),
       ( '48b7694f-0830-49ec-9e0f-2e4f13411796', '4fdb1e44-6d6b-43cc-9c08-7c0cd0ffca15', 'Video'
       , 'java for intermediates', 'www.java.com', 'INTERMEDIATE'),
       ( '5d851366-9ee6-4c51-a708-bdfb393bd798', '4fdb1e44-6d6b-43cc-9c08-7c0cd0ffca15', 'Website'
       , 'java for advanced', 'www.java.com', 'EXPERT'),
       ( '2d152ff5-b8b0-4936-bd7c-c9b88694fef5', 'c279fc3e-a9bc-4ec9-a430-5d9a1315def1', 'Website'
       , 'Python for newbies', 'www.Python.com', 'NOVICE'),
       ( 'abe636c2-35e4-404f-a08c-0dca86781ae2', 'c279fc3e-a9bc-4ec9-a430-5d9a1315def1', 'Video'
       , 'Python for intermediates', 'www.Python.com', 'INTERMEDIATE'),
       ( 'da4b1663-f49e-405e-9f9b-dfa76f1e8d33', 'c279fc3e-a9bc-4ec9-a430-5d9a1315def1', 'Website'
       , 'Python for advanced', 'www.Python.com', 'EXPERT'),
       ( 'ff61bc44-d5da-49c6-ace1-039bbb78213f', '35591dd2-9300-459e-a00e-d1db15cf6a3a', 'Website'
       , 'TheHeart for newbies', 'www.CPU.com', 'NOVICE'),
       ( 'a48e2fdc-bf6a-4800-822a-764459e73e96', '35591dd2-9300-459e-a00e-d1db15cf6a3a', 'Video'
       , 'TheHeart for intermediates', 'www.CPU.com', 'INTERMEDIATE'),
       ( '1ad64630-65ba-424e-b5f8-5ffff0d655d7', '35591dd2-9300-459e-a00e-d1db15cf6a3a', 'Website'
       , 'TheHeart for advanced', 'www.CPU.com', 'EXPERT');



INSERT INTO roadmap_resources (id, roadmap_id, revision_resource_id)
VALUES ('0dbabf29-a60f-459c-8419-3628a56c2e68', '8894517b-539a-4b89-b0a0-849e84329181',
        '4c059778-5c0e-40f0-ae0b-85bf0ce8b6cc'),
       ('6033160c-c5f5-4459-96c5-5c21f4d34048', '8894517b-539a-4b89-b0a0-849e84329181',
        '2d927d0a-d2a1-46d7-826d-e6783da22169');

INSERT INTO subjects(id, subject_name)
VALUES ('30d00a6f-9577-418f-9f62-04d940379102', 'Maths'),
       ('4921cbae-589d-43f6-b069-cbca45a8b40c', 'English'),
       ('482f9580-e72b-41d8-b1c3-d8e2162fbe20', 'CompSci'),
       ('7b301086-ead1-45ab-86a3-11eafc4820c7', 'Biology'),
       ('ae5a154e-2957-489c-b9b3-7f1046cae743', 'chemistry'),
       ('8830e1d8-47fb-4260-bbcc-1f05a437f5ed', 'physics');

INSERT INTO topics(id, subject_id, topic_name)
VALUES ('22be771a-7803-445f-b88f-732fd6170f56', '30d00a6f-9577-418f-9f62-04d940379102', 'algebra'),
       ('1915b4be-7f11-48bb-97ff-88f9297104f8', '30d00a6f-9577-418f-9f62-04d940379102', 'calculus'),
       ('af9253a5-9c4f-4095-9778-fadd3a288935', '30d00a6f-9577-418f-9f62-04d940379102', 'addition'),
       ('24fb37ab-18ed-42c7-9bc3-09b750aca27a', '4921cbae-589d-43f6-b069-cbca45a8b40c', 'EnglishLit'),
       ('c431c80b-31c6-41c2-99e2-ab6690d4da85', '4921cbae-589d-43f6-b069-cbca45a8b40c', 'EnglishLanguage'),
       ('79129a45-bc7e-4bdf-9a09-8421609ebdc8', '482f9580-e72b-41d8-b1c3-d8e2162fbe20', 'CPU'),
       ('4fdb1e44-6d6b-43cc-9c08-7c0cd0ffca15', '482f9580-e72b-41d8-b1c3-d8e2162fbe20', 'java'),
       ('c279fc3e-a9bc-4ec9-a430-5d9a1315def1', '482f9580-e72b-41d8-b1c3-d8e2162fbe20', 'python'),
       ('35591dd2-9300-459e-a00e-d1db15cf6a3a', '7b301086-ead1-45ab-86a3-11eafc4820c7', 'The heart'),
       ('4759faee-9275-4ea9-9d85-fdcfe1919d1b', '7b301086-ead1-45ab-86a3-11eafc4820c7', 'The legs'),
       ('52b4b41e-1190-4722-a1f5-b233f64a37e6', '8830e1d8-47fb-4260-bbcc-1f05a437f5ed', 'Quarks'),
       ('9d13c738-f7dc-43c5-aa9c-71cc8c2f1fd0', '8830e1d8-47fb-4260-bbcc-1f05a437f5ed', 'light waves'),
       ('ee1dc434-aaca-4dd9-8f38-cafbee5b9112', '8830e1d8-47fb-4260-bbcc-1f05a437f5ed', 'E=m^2');
