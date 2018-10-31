# clean-machine [![Build Status](https://travis-ci.com/eu-team/clean-machine.svg?branch=master)](https://travis-ci.com/eu-team/clean-machine) [![Maintainability](https://api.codeclimate.com/v1/badges/6d0cb851cc1fe3103d51/maintainability)](https://codeclimate.com/github/eu-team/clean-machine/maintainability)

## Installation

## PostgreSQL

1. Open pgAdmin interface (in PostgreSQL install folder, under the `bin` directory, double click on `pgAdmin4.exe`)
2. Create a user with the following credentials:
```
username: User
password: password
```
3. Create a PostreSQL database named `cleanMachineDB`, and assign `User` as the database owner

## IntelliJ IDEA

1. Open IntelliJ and select "Import Project"
2. Choose "Maven" and click Next
3. Tick "Import Maven projects automatically" and Next
4. Leave all the defaults and complete the assistant

IntelliJ should import all Maven dependencies automatically.

You can test the server by navigating to [http://localhost:8080/users](http://localhost:8080/users) that should display a JSON list of users.