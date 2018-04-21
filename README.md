# Contact book demo app

This project was made as a test project which features Spring Boot, Apache Derby
and Maven.

The constraints was to build a contact book with could provide a list of
contacts, which in turn are made up of names and phone numbers. Through REST
calls, there should be options to create, list and delete records in the
contact book.

## Class DBConnector

This is a class with two public methods, `create` and `getConnection`. My
thinking here was that everytime the application starts, it is a good time to make sure
the database itself and the tables exist, so `create` is called upon booting.
`create` then proceeds to call `createDatabase` and `createTable`, which in
turn are private.

`getConnection` returns a `Connection` instance, not much to say here really.

## ContactsDao

The data access object for contacts.

## Kinks

Some petulances I have regarding the solution;

* The database name is hardcoded, and is not password protected.
* Error handling is not optimal. Mostly I catch a generic `Exception` and log it
to the console. Ideally  I would want to catch specific exceptions to know the
exact state of the program.
* Normally I would use a Business object to handle all the business logic, but
it turned out not to be a lot of that, so I figured I could put all the logic in
the data access object.
* I couldn't get the redirect to work across the controllers. I guess it is some
Spring quirk I didn't get the hang of, so now the form processing lies in the
web controller instead of the api controller.
