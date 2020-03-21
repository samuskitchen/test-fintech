# Fintech

It's the Makers Solutions Test. In order to test or run the project you must install the lombok plugin in your IDE :

- [Intellij](https://projectlombok.org/setup/intellij)
- [Eclipse](https://projectlombok.org/setup/eclipse)

## Installation

The project uses maven to automate the construction of the project, please download the dependencies and then execute the following commands

```bashc
mvn clean install
``` 

## Flyway
The project uses flyway to generate the tables to configure it we must change the dataSource configuration of the appication.properties file, but before that a database must be created


