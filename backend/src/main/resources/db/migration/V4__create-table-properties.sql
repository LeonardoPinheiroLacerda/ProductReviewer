CREATE TABLE "properties" (
    "id" UUID,
    "category_id" UUID,
    "name" VARCHAR(32),
    "default_value" VARCHAR(32),
    "type" VARCHAR(16)
);

ALTER TABLE "properties"
ADD CONSTRAINT "properties_pk" PRIMARY KEY ("id");

ALTER TABLE "properties"
ADD CONSTRAINT "properties_category_fk" FOREIGN KEY ("category_id") REFERENCES "categories" ("id");