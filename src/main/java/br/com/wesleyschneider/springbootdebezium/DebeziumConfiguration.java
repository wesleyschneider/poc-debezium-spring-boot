package br.com.wesleyschneider.springbootdebezium;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConfiguration {
    @Bean
    public io.debezium.config.Configuration oldDatabaseConnector() {
        return io.debezium.config.Configuration.create()
                .with("name", "estudante-sqlserver-connector")
                .with("connector.class", "io.debezium.connector.sqlserver.SqlServerConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", "localhost")
                .with("database.port", "1432")
                .with("database.user", "POC-CDC")
                .with("database.password", "Abcd1234")
                .with("database.names", "OLD_DATABASE")
                .with("database.encrypt", "false")
                .with("snapshot.mode", "initial_only")
                .with("topic.prefix", "seila")
                .with("table.include.list", "dbo.Estudante")
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", "/tmp/schemaHistory.dat")
                .build();
    }
}
