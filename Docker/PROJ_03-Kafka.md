# Project 3: Set up Kafka

- [Spring Initializer](https://start.spring.io/)
- [Kafka Quickstart](https://kafka.apache.org/quickstart)
- [apache/kafka | Docker Hub](https://hub.docker.com/r/apache/kafka)
- ['apache/kafka' Docker Image Usage Guide | GitHub](https://github.com/apache/kafka/blob/trunk/docker/examples/README.md)

## Env Setup Notes



## docker-compose.yaml

```yaml
services:services:
  kbroker:
    image: apache/kafka:latest
    container_name: kbroker
    ports:
      - 9092:9092
    volumes:
      - ./kafka_logs:/tmp/kraft-combined-logs
    environment:
      KAFKA_NODE_ID: 1
      # KRaft setting (no Zookeeper needed)
      KAFKA_PROCESS_ROLES: broker,controller
      
      # Listeners
      # CONTROLLER binds to localhost (internal only)
      # PLAINTEXT binds to 0.0.0.0 (accepts external connections)
      KAFKA_LISTENERS: CONTROLLER://localhost:9093,PLAINTEXT://0.0.0.0:9092
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka:9093
      
      # Cluster ID (generate w/ 'kafka-storage.sh random-uuid')
      # CLUSTER_ID: '4L6g3nShT-eMCtK--X86sw'
      
      # Single-node cluster settings
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

volumes:
  kafka_data:
    driver: local
```

_____

## Challenges Encountered
1. **Context:** <br>**Error:** broker-1  | Exception in thread "main" java.lang.IllegalArgumentException: requirement failed: controller.listener.names must contain at least one value appearing in the 'listeners' configuration when running the KRaft controller role at scala.Predef$.require(Predef.scala:337) at kafka.server.KafkaConfig.validateAdvertisedControllerListenersNonEmptyForKRaftController$1(KafkaConfig.scala:649)<br>**Obs:**
1. **Context:** Issue connecting from Spring Boot app to Kafka Docker image.<br>**Error:** ERROR Unable to start acceptor for ListenerName(CONTROLLER) (kafka.network.DataPlaneAcceptor)
broker-1  | org.apache.kafka.common.KafkaException: Socket server failed to bind to kafka:9093: Unresolved address. <br>**Obs:** Container starts successfully.
    - **Solution:** `KAFKA_LISTENERS: CONTROLLER://localhost:9093,PLAINTEXT://0.0.0.0:9092`. Binding to 0.0.0.0 allows external connections. Localhost for internal connections only.
1. **Context:** <br>**Issue:** <br>**Obs:** 
    - **Solution:** 
    - **!REF**
1. **Context:** <br>**Issue:** <br>**Obs:** 
    - **Solution:** 
    - **!REF**