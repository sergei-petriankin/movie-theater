CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  dob        TIMESTAMP
);

CREATE TABLE events (
  id         INTEGER PRIMARY KEY,
  name       VARCHAR(30),
  base_price DOUBLE,
  rating     INTEGER
);

CREATE TABLE tickets (
  id        INTEGER PRIMARY KEY,
  seat      INTEGER,
  date_time TIMESTAMP,
  user_id   INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id),
  event_id  INTEGER,
  FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE event_counter (
  id                 INTEGER PRIMARY KEY,
  event_counter_case INTEGER,
  count              BIGINT

);

CREATE TABLE discount_counter (
  id                INTEGER PRIMARY KEY,
  discount_strategy VARCHAR(50),
  count             BIGINT,
  user_id           INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id),
)


