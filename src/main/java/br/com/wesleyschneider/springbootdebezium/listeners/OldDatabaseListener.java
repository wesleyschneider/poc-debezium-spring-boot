package br.com.wesleyschneider.springbootdebezium.listeners;

import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Struct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class OldDatabaseListener {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    public OldDatabaseListener(Configuration estudanteConnectorConfiguration) {
        this.debeziumEngine = DebeziumEngine
                .create(ChangeEventFormat.of(Connect.class))
                .using(estudanteConnectorConfiguration.asProperties()).notifying(this::handleChange)
                .build();
    }

    private void handleChange (RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();
        Struct sourceRecordChangeValue = (Struct) sourceRecord.value();


    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }
}
