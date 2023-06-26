CREATE TABLE "categories" (
    "id" UUID,
    "description" VARCHAR(64) NOT NULL
);

ALTER TABLE "categories"
ADD CONSTRAINT "categories_pk" PRIMARY KEY ("id");

ALTER TABLE "categories"
ADD CONSTRAINT "categories_description_unique" UNIQUE("description");