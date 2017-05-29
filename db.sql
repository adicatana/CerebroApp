CREATE TABLE "role" (
  "roleid" SERIAL PRIMARY KEY,
  "role" VARCHAR(30) NOT NULL
);

CREATE TABLE "topic" (
  "topicid" SERIAL PRIMARY KEY,
  "topicname" VARCHAR(30) NOT NULL
);

CREATE TABLE "question" (
  "questionid" SERIAL PRIMARY KEY,
  "topicid" INTEGER UNIQUE NOT NULL,
  "question" VARCHAR(300) NOT NULL,
  "answer" VARCHAR(300) NOT NULL,
  "wrong1" VARCHAR(300) NOT NULL,
  "wrong2" VARCHAR(300) NOT NULL,
  "topic" INTEGER NOT NULL
);

CREATE INDEX "idx_question__topic" ON "question" ("topic");

ALTER TABLE "question" ADD CONSTRAINT "fk_question__topic" FOREIGN KEY ("topic") REFERENCES "topic" ("topicid");

CREATE TABLE "user" (
  "userid" SERIAL PRIMARY KEY,
  "login" VARCHAR(30) UNIQUE NOT NULL,
  "password" VARCHAR(30) UNIQUE NOT NULL,
  "rating" INTEGER NOT NULL,
  "email" VARCHAR(50) UNIQUE NOT NULL,
  "name" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "roleuser" (
  "roleuserid" SERIAL PRIMARY KEY,
  "userid" INTEGER UNIQUE NOT NULL,
  "roleid" INTEGER UNIQUE NOT NULL,
  "user" INTEGER NOT NULL,
  "role" INTEGER NOT NULL
);

CREATE INDEX "idx_roleuser__role" ON "roleuser" ("role");

CREATE INDEX "idx_roleuser__user" ON "roleuser" ("user");

ALTER TABLE "roleuser" ADD CONSTRAINT "fk_roleuser__role" FOREIGN KEY ("role") REFERENCES "role" ("roleid");

ALTER TABLE "roleuser" ADD CONSTRAINT "fk_roleuser__user" FOREIGN KEY ("user") REFERENCES "user" ("userid");

CREATE TABLE "userquestion" (
  "userquestionid" SERIAL PRIMARY KEY,
  "userid" INTEGER UNIQUE NOT NULL,
  "questionid" INTEGER UNIQUE NOT NULL,
  "lastattempt" BOOLEAN NOT NULL DEFAULT null,
  "user" INTEGER NOT NULL,
  "question" INTEGER NOT NULL
);

CREATE INDEX "idx_userquestion__question" ON "userquestion" ("question");

CREATE INDEX "idx_userquestion__user" ON "userquestion" ("user");

ALTER TABLE "userquestion" ADD CONSTRAINT "fk_userquestion__question" FOREIGN KEY ("question") REFERENCES "question" ("questionid");

ALTER TABLE "userquestion" ADD CONSTRAINT "fk_userquestion__user" FOREIGN KEY ("user") REFERENCES "user" ("userid")