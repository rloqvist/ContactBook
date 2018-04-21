# Contact book demo app

This project was made as a test project which features Spring Boot, Apache Derby
and Maven.

The constraints was to build a contact book with could provide a list of
contacts, which in turn are made up of names and phone numbers. Through REST
calls, there should be options to create, list and delete records in the
contact book.

# Howto

### Maven

Build and start the app with;

```bash
mvn -U clean install && mvn spring-boot:run
```

Especially keep the `-U`-flag to download the Spring Boot sources.

### Other

Here are the dependencies listed in the `pom.xml` that you will need to install.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.apache.derby</groupId>
        <artifactId>derby</artifactId>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
        <type>jar</type>
    </dependency>
</dependencies>
```

The `spring-boot-devtools` are indeed optional.

## Class DBConnector

This is a class with two public methods, `create` and `getConnection`. My
thinking here was that every time the application starts, it is a good time to
make sure the database itself and the tables exist, so `create` is called upon
booting. `create` then proceeds to call `createDatabase` and `createTable`,
which in turn are private.

`getConnection` returns a `Connection` instance, not much to say here really.

## Front-end

For front-end I use Thymeleaf for templating and bean-backed form validation. I
use jQuery for ajax, and jQuery datatables for data display. Datatables are very
nifty for pagination, sorting and filtering, and it just works out of the box.

## SQL

For table creation, I keep the schema in an external file, which is loaded and
used upon app init.

For the actions, I use prepared strings as static variables in my DAO-class,
which I do not really worry about being prone to SQL-injection.

## Tests

Since this is a web-app, testing is inherently difficult. However, since I'm not
using an ORM, the contact-fields could be messy to update and maintain, should
the application grow. This made me add tests to make sure the `Contact` and
`ContactForm` has the right attributes.

## Kinks

Some petulances I have regarding the solution;

* The database name is hard coded, and is not password protected.
* Error handling is not optimal. Mostly I catch a generic `Exception` and log it
to the console. Ideally  I would want to catch specific exceptions to know the
exact state of the program.
* Normally I would use a Business object to handle all the business logic, but
it turned out not to be a lot of that, so I figured I could put all the logic in
the data access object.
* I couldn't get the redirect to work across the controllers. I guess it is some
Spring quirk I didn't get the hang of, so now the form processing lies in the
web controller instead of the API controller.
