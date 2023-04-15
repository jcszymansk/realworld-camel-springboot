# ![RealWorld Example App](logo.png)

## [Apache Camel](https://camel.apache.org/) codebase containing real world examples (CRUD, auth, advanced patterns, etc) that adheres to the [RealWorld](https://github.com/gothinkster/realworld) spec and API.


## [RealWorld](https://github.com/gothinkster/realworld)


This codebase was created to demonstrate a fully fledged backend
application built with **Apache Camel** including CRUD operations, 
authentication, routing, pagination, and more.

I've gone to great lengths to adhere to the **Apache Camel** community 
styleguides & best practices.

For more information on how to this works with other frontends/backends,
head over to the [RealWorld](https://github.com/gothinkster/realworld) 
repo.


# How it works

This is an Apache Camel application working in a Spring Boot environment.
Although it was tested only with Hibernate and H2, it should work with any
JPA 2.1 implementation and any database supported by it.

It directly uses the following standard Camel components/data 
formats/languages:
- `camel-groovy`
- `camel-jackson`
- `camel-jpa`
- `camel-jslt`
- `camel-jsonata`
- `camel-sql`

Also, I've created two custom Camel components to use in this demo:
- `camel-jwt`
- `camel-xjpa`

### `camel-jwt` 
is a simple component that allows to sign and verify JWT tokens,
and to extract the claims from them. It uses the `jose4j` library, but
supports only the `HS256` algorithm (and, for testing purposes, `none`).
This component is retrieved by maven from my repository, as declared in
the `pom.xml` file.

### `camel-xjpa` 
is a component that extends the `camel-jpa` component to add
the following features:
- `outputType` parameter to specify the type of the output, which can be
`Default` which returns a list on queries and an object or null on finds (the default `camel-jpa` behavior); or 
`SelectOne`, which will return a single object or throw an exception if no results or more than one result is found.
- `outputTarget` parameter to specify where to put the output, if not
into the message body.
- `firstResult` to specify the offset in the generated SQL.

This component is embedded in this project, in the 
`com.github.jacekszymanski.camel.xjpa` package.

# How to run

To run this example you need to have Java 11 and Maven installed. 

You need also to create a JWT secret. On Mac/Linux you should be able
to to this using the `mkkey.sh` script in the `src/main/resources`
directory.

Once you have your key, simply run:
```
mvn spring-boot:run
```

This projects has also several (could be more...) tests. To run them,
issue the following command:
```
mvn test
```

The Java tests and also the curl commands in `src/test/shell` directory
use the test JWT key from the `src/test/resources/jwt.key.txt` file. To
run the curl commands you need to copy the test key to the 
`src/main/resources` directory or otherwise make it available in the
classpath.

To change the JPA implementation used, add it to the `pom.xml` file 
instead of the Hibernate dependency. Also, you probably need to update
the `application.yml` file. To change the database, change the dependency
in the `pom.xml` file and update the `application.yml` file as well.
