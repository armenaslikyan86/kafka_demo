# Simple app for using Apache Kafka messaging platform

This application is a Spring Boot based Java project designed to facilitate basic interaction with an Apache Kafka messaging system. Its primary purpose is to allow users or client applications to send messages to a Kafka topic and retrieve messages that have been consumed from a Kafka topic via simple HTTP endpoints.

## Core Functionality and Data Flow

The application operates with two main workflows: sending messages to Kafka and receiving messages from Kafka.

**Sending Messages:**

1.  A client application sends an HTTP `POST` request to the `/kafka/send` endpoint. The body of this request should contain the raw string data that needs to be sent as a message.
2.  The `KafkaController` component within the application receives this request.
3.  It then passes the string message to the `KafkaProducer` service.
4.  The `KafkaProducer` takes this string and publishes it to a specific Apache Kafka topic. The topic name is configured in `src/main/resources/application.properties` (usually under the key `app.topic`).

**Receiving Messages:**

1.  The `KafkaConsumer` service within the application continuously listens to the configured Kafka topic (defined by `app.topic` and Kafka group ID `spring.kafka.groupId` in `application.properties`).
2.  When a new message arrives on the topic, the `KafkaConsumer` reads it as a raw string.
3.  It stores these received string messages in an internal, in-memory list.
4.  A client application can retrieve these stored messages by sending an HTTP `GET` request to the `/kafka/receive` endpoint.
5.  The `KafkaController` handles this request and returns the list of accumulated string messages as a JSON array in the HTTP response. Note that this list contains all messages received since the application instance started.

## How to Use the Application

1.  **Prerequisites:**
    *   **Apache Kafka Instance:** Ensure you have an Apache Kafka broker (or cluster) running and accessible to this application. The connection details (bootstrap servers) are configured in `src/main/resources/application.properties` (e.g., `spring.kafka.bootstrap-servers=localhost:9092`). The PlantUML diagram (`src/main/resources/kafka_api_flow.puml`) also noted a default of `localhost:9092`.
    *   **Kafka Topic:** The Kafka topic (e.g., `app.topic`) must exist, or your Kafka broker must be configured for automatic topic creation.
    *   **Application Running:** The Spring Boot application itself must be built and running. You can typically run it using a command like `mvn spring-boot:run` from the project's root directory, or by executing the packaged JAR file.

2.  **Sending a Message:**
    *   Use any HTTP client tool (e.g., `curl`, Postman).
    *   **Method:** `POST`
    *   **URL:** `http://<host>:<port>/kafka/send` (replace `<host>` and `<port>` with the application's host and port, e.g., `http://localhost:8080/kafka/send`).
    *   **Headers:** `Content-Type: text/plain` (for a plain string).
    *   **Body:** The raw string content you want to send.

    *Example using `curl`:*
    ```bash
    curl -X POST -H "Content-Type: text/plain" --data "Hello from my Kafka client!" http://localhost:8080/kafka/send
    ```
    A successful request will typically return an HTTP 200 OK status with an empty body.

3.  **Receiving Messages:**
    *   Use any HTTP client tool.
    *   **Method:** `GET`
    *   **URL:** `http://<host>:<port>/kafka/receive` (e.g., `http://localhost:8080/kafka/receive`).

    *Example using `curl`:*
    ```bash
    curl http://localhost:8080/kafka/receive
    ```
    The response will be a JSON array of strings, where each string is a message consumed from the topic. For example:
    ```json
    ["Message one", "Another message", "Hello from my Kafka client!"]
    ```
    If no messages have been received, an empty array `[]` is returned.

## Additional Components (Potentially Unused in Core Flow)

The codebase includes:
*   `SourceData.java` (model class)
*   `DestinationData.java` (model class)
*   `BeanMapper.java` (for mapping `SourceData` to `DestinationData` using the Orika library)
*   `NewlineEscapeConverter.java` (used by `BeanMapper`)

While the PlantUML diagram (`src/main/resources/kafka_api_flow.puml`) hinted at a flow involving these components for data transformation, the current implementation of the `/kafka/send` and `/kafka/receive` endpoints directly handles raw strings. These components do not appear to be active in this primary message path. They might be remnants of a previous design, intended for other features, or used in contexts not directly exposed by these specific endpoints. The `BeanMapper` contains a commented-out `main` method, suggesting it might have been for standalone testing.

## Configuration Details

Key configuration for the application, such as Kafka bootstrap server addresses and topic names, can be found and modified in:
*   `src/main/resources/application.properties`

Look for properties like:
*   `app.topic` (for the Kafka topic name)
*   `spring.kafka.bootstrap-servers` (for the Kafka broker addresses)
*   `spring.kafka.consumer.group-id` (for the Kafka consumer group ID)
```
