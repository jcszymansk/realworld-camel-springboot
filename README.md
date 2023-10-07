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
- `camel-sql`

(there used to be `camel-jsonata` as well, but it was completely replaced by `camel-jslt`)

Also, I've created two custom Camel components to use in this demo:
- `camel-jwt`
- `camel-xjpa` (removed)

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

#### Update 2023-10-08 (commit a71279be)

With the release of Camel 3.21.0 the `camel-xjpa` component is no longer needed, as
all the features it provided were added to the standard `camel-jpa` component.

Accordingly, the project now uses the `camel-jpa` component instead of `camel-xjpa`
and the `camel-xjpa` component has been removed from the project.

# How to run

To run this example you need to have Java 11 and Maven installed. 

You need also to create a JWT HS256 secret. On Mac/Linux you should be able
to to this using the `mkkey.sh` script in the `src/main/resources`
directory.

Once you have your key, simply run:
```
mvn spring-boot:run
```

### Frontend
This project is a backend only. To see it in action, you need to run a frontend as well.
Choose one from [here](https://codebase.show/projects/realworld?category=frontend), install
it and point to the backend at [http://localhost:8080/api/](http://localhost:8080/api/).

### ...or just use the Docker image:
```
docker pull jszymanski/realworld-camel-springboot
docker container create -t --name realworld-camel-springboot -p 127.0.0.1:8080:80 jszymanski/realworld-camel-springboot:latest
docker container start -a realworld-camel-springboot
```

and access from the browser at http://localhost:8080/

It is configured to use the test key, so you can run the test curl commands right away.

The Docker image uses [this frontend](https://github.com/mits-gossau/event-driven-web-components-realworld-example-app).
I am not the author of this frontend, and use it unchanged. Any errors you'll see are probably
my fault, not the author's. :-)

### Tests

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

# What's next

The implementation is complete with the spec, though it probably contains a fair share of bugs,
I will definitely be fixing them when found.

To showcase asynchronous Camel components, some additional features could be added, like
a cron job sending usage statistics or something similar. I will look into this. OTOH,
features like confirmation emails on account creation or password reset are unsupported
by the existing frontends, so there's no point in implementing them until the spec is
updated.

Some time after Camel 4.0 is released, I will also update this project to use it.

Possibly rewrite datagrabber in Java or even Camel so it is possible to preserve
created/updated timestamps on articles and comments. (Now it uses the API which does
not allow to set these fields.) In the pulled data, line breaks are sometimes quoted too
much, look into correcting this.

Limit title/description length (which would affect the imported data, as the titles are
often very long there).

Use Spring Security for authentication and route policies.

The Docker image creation is lame, I should properly do two or even three images and
docker-compose them together.

And certainly much more...
