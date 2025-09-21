# flowcrux

# Running the system

The following will start the server on port 9818. There is an
included [Swagger UI](http://localhost:9818/swagger-ui/index.html).

```
mvn clean && mvn spring-boot:run
```

# Running Camunda

```bash
docker compose up
```

Most of
the [login credentials](https://docs.camunda.io/docs/self-managed/setup/deploy/local/docker-compose/?cli=without)
are

- Login: demo
- Password: demo