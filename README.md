# Product Reviewer

Demonstration project for exercise using spring boot GraphQL implementation on backend and React.js on frontend.

## Requeriments

You will need to have installed on your machine:
 - Java (at least version 17)
 - Docker (with docker-compose configured)

## How to run

First download or clone this git repository:

~~~ sh
    git clone https://github.com/LeonardoPinheiroLacerda/ProductReviewer.git
~~~

Enter the project folder and run the database container:

~~~ sh
    docker-compose up -d
~~~

And inside the ```Backend``` folder run the spring boot application:

~~~ sh 
    ./mvnw spring-boot:run
~~~

If you are using linux maybe you will need to provide execution right to the mvnw file, you can use the following command before the previous step:

~~~ sh
    sudo chmod +x mvnw
~~~

### Database diagram

You can reach the interactive diagram on [here](https://dbdiagram.io/d/6467904ddca9fb07c4676c7b).

![Database diagram](/docs/dbDiagram.png)

