CREATE TABLE "products" (
    "id" UUID,
    "category_id" UUID,
    "description" VARCHAR(64) NOT NULL,
    "price" FLOAT
);

ALTER TABLE "products"
ADD CONSTRAINT "products_pk" PRIMARY KEY ("id");

ALTER TABLE "products"
ADD CONSTRAINT "products_category_fk" FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "products"
ADD CONSTRAINT "products_description_unique" UNIQUE ("description");