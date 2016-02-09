--name: create-organization!
-- creates a new organization
INSERT INTO organizations
 (name, domain)
VALUES (:name, :domain)

--name: update-organization!
-- updates an existing organization
UPDATE organizations
SET name = :name, domain = :domain
WHERE id = :id

-- name: get-organization-by-domain
-- retrieve an organization given the domain.
SELECT * FROM organizations
WHERE domain = :domain

-- name: delete-organization!
-- delete an organization given the id
DELETE FROM organizations
WHERE id = :id

-- name: create-user!
-- creates a new user record
INSERT INTO users
(organization_id, name, last_name, email, created_at, updated_at)
VALUES (:organization_id, :name, :email, :created_at, :updated_at)

-- name: update-user!
-- update an existing user record
UPDATE users
SET organization_id = :organization_id, name = :name, email = :email, created_at = :created_at, updated_at = :updated_at
WHERE id = :id

-- name: get-user
-- retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- name: delete-user!
-- delete a user given the id
DELETE FROM users
WHERE id = :id
