CREATE TABLE "products_properties" (
    "product_id" UUID,
    "property_id" UUID,
    "value" VARCHAR NOT NULL
);

ALTER TABLE "products_properties"
ADD CONSTRAINT "products_properties_pk" PRIMARY KEY ("product_id", "property_id");

ALTER TABLE "products_properties"
ADD CONSTRAINT "products_properties_products_fk" FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "products_properties"
ADD CONSTRAINT "products_properties_properties_fk" FOREIGN KEY ("property_id") REFERENCES "properties" ("id");