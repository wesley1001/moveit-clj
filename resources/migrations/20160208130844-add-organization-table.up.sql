CREATE TABLE organizations
(id SERIAL PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 domain VARCHAR(50) NOT NULL);

;--

CREATE UNIQUE INDEX organizations_domain_idx ON organizations (domain);
