CREATE SCHEMA IF NOT EXISTS chatbot;

CREATE TABLE IF NOT EXISTS chatbot.employee (
  id   UUID NOT NULL,
  name TEXT NOT NULL,
  age  BIGINT,
  PRIMARY KEY (id)
);

INSERT INTO chatbot.employee VALUES ('36f4363c-4a0e-4c45-b813-9578816b8f66', 'Bob', 1337);
INSERT INTO chatbot.employee VALUES ('36f4363c-4a0e-4c45-b813-9578816b8f67', 'Mike');
