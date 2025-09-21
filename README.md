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

### Connecting to PostgreSQL

```bash
docker exec -it postgres_flowcrux psql -U mourjo -d flowcrux_db 
```

### Creating a Kafka topic

```bash
docker exec -it flowcrux_kafka_broker /bin/kafka-topics --create --topic flowcrux_ops --bootstrap-server flowcrux_kafka_broker:29092 
```

### Producing to a Kafka topic

```bash
docker exec -it flowcrux_kafka_broker /bin/kafka-console-producer --bootstrap-server flowcrux_kafka_broker:29092 --topic flowcrux_ops
```

### Consuming from a Kafka topic

```bash 
docker exec -it flowcrux_kafka_broker /bin/kafka-console-consumer --bootstrap-server flowcrux_kafka_broker:29092 --topic flowcrux_ops
 ```
