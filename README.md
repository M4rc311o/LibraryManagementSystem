# BPC-BDS-Project
<p align="center">
    <img src="/App/src/main/resources/org/but/feec/bds/images/lms_logo.png" width="200">
</p>

## Overview
The basic idea of this project is an application for library network management, providing access to selected information and functionalities for both library staff and standard users. Project consists of JavaFX application, PostgreSQL database running in Docker container managed by Flyway database-migration tool also running in Docker container.
## Goal
Goal of the project was not only to create JavaFX application with PostgreSQL database from previous assignments, but also learn how to everything join together. And get familiar with other tools when deploying such application, for example various logging tools, version control systems like Gitlab for source code or Flyway for database, containerization options like Docker and also get some kind of idea about workflow and research needed for accomplishing similar tasks.
## Build & Run
First requirement is that Java 17 and Maven has to be installed. Further to start the application from scratch a few simple steps have to be performed:
### Generate SSL certificates
All communication between JavaFX application and all containers is secured by SSL with not only server but also client authentication. So server self-signed certificate together with client certificates has to be generated. In order to not include private keys in my Gitlab repository I have created a simple script for Windows `KeyCertGen.cmd` and for GNU/Linux `KeyCertGen.sh` that can generate brand new set of keys/certificates and place them in correct directories. The only requirement is to have openssl installed and run the script directly from where is located, so that all keys/certificates end up in correct locations from where are loaded by containers and application.
### Docker compose
Next step is to run `docker compose` command with `compose.yaml` file placed in Database directory.
### JavaFX App
Last step is move to the App directory compile JavaFX application with `mvn clean install` and than run it with `java -jar .\target\bds-project-app-1.0.jar`.
### Log in credentials
Each user is logged in by its username and password, password of each user is set to be their username with year when they were born. This information can be extracted from Flyway migration `V3__db_data.sql`, but here are some valid sets of credentials for both types of accounts:
1. **Standard user** account:
    - **username:** `carterp`
    - **password:** `carterp1983`
2. **Librarian** account:
    - **username:** `sofiah`
    - **password:** `sofiah2002`
### Summary
```
git clone <repository>
cd .\BPC-BDS-Project\
.\KeyCertGen.cmd
docker compose -f .\Database\compose.yaml up -d
cd .\App\
mvn clean install
java -jar .\target\bds-project-app-1.0.jar
```
Note: *Some commands needs to be altered when GNU/Linux is used.*
