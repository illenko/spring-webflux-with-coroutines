#Apache Kafka Installation for local development

Until Docker does not have Kafka image for Mac with M1 chip, I need to use local Kafka instance.

Steps to set up:

1. `brew install kafka`
2. `/opt/homebrew/opt/kafka/bin/zookeeper-server-start /opt/homebrew/etc/kafka/zookeeper.properties`
3. `/opt/homebrew/opt/kafka/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties`

To connect service with Kafka see `storage.kafka` properties in `application.yaml`