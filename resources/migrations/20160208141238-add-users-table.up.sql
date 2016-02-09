CREATE TABLE users
(id SERIAL PRIMARY KEY,
 organization_id INTEGER REFERENCES organizations(id),
 name VARCHAR(50) NOT NULL,
 email VARCHAR(50) NOT NULL,
 created_at TIMESTAMP NOT NULL,
 updated_at TIMESTAMP NOT NULL);
