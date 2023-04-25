package br.com.wesleyschneider.springbootdebezium.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConfiguration {
    @Value("${debezium.database.hostname}")
    private String hostname;

    @Value("${debezium.database.port}")
    private String port;

    @Value("${debezium.database.user}")
    private String user;

    @Value("${debezium.database.password}")
    private String password;

    @Value("${debezium.database.names}")
    private String names;

    @Value("${debezium.database.tableList}")
    private String tableList;

    @Bean
    public io.debezium.config.Configuration oldDatabaseConnector() {
        return io.debezium.config.Configuration.create()
                .with("name", "estudante-sqlserver-connector")
                .with("connector.class", "io.debezium.connector.sqlserver.SqlServerConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", hostname)
                .with("database.port", port)
                .with("database.user", user)
                .with("database.password", password)
                .with("database.names", names)
                .with("database.encrypt", "false")
                .with("table.include.list", tableList)
                .with("snapshot.mode", "initial")
                .with("topic.prefix", "seila")
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", "/tmp/schemaHistory.dat")
                .with("include.schema.changes", "false")
                .build();
    }
}
