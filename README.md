# sample-project

Project is comprised of 3 microservices and a postgres db.
1. eureka-server
2. gateway-server
3. demo_app

Project configuration contains two profiles:
1. local-dev
2. docker-dev

In order to run the service from an IDE, use the local-dev profile. To run the service in a docker env select the docker-dev profile.

To run the services in docker use the compose-services.yaml file which is located in the main directory.

---
As a first step start the postgres database and execute the USER_PRIVILEGES.sql file (located in the resources folder of the demo_app) in order to create a new user with proper rights in order to setup the db schema.

Once user with privileges is created, the demo_app service should be able to run and create the necessary tables.

Additional data scripts are provided to populate the tables with data.

- Note: If user and privileges are not created properly, the application will not be able to create a connection with the database and application will not run

---
eureka-server and gateway-server are not necessary to be deployed.
