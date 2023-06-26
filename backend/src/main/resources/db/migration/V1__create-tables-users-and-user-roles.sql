CREATE TABLE "users" (
    "id" UUID,
    "username" VARCHAR(64),
    "password" VARCHAR
);

CREATE TABLE "user_roles" (
    "user_id" UUID,
    "role" VARCHAR(16)
);

ALTER TABLE "users"
ADD CONSTRAINT "users_pk" PRIMARY KEY ("id");

ALTER TABLE "user_roles"
ADD CONSTRAINT "user_roles_pk" PRIMARY KEY ("user_id", "role");

ALTER TABLE "users"
ADD CONSTRAINT "users_username_unique" UNIQUE ("username");