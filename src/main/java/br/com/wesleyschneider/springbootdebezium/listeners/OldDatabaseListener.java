package br.com.wesleyschneider.springbootdebezium.listeners;

import br.com.wesleyschneider.springbootdebezium.service.EstudanteService;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static io.debezium.data.Envelope.FieldName.*;
import static io.debezium.data.Envelope.Operation;

@Component
public class OldDatabaseListener {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    @Autowired
    private EstudanteService estudanteService;

    public OldDatabaseListener(Configuration estudanteConnectorConfiguration) {
        this.debeziumEngine = DebeziumEngine
                .create(ChangeEventFormat.of(Connect.class))
                .using(estudanteConnectorConfiguration.asProperties()).notifying(this::handleChange)
                .build();
    }

    private void handleChange(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        Struct sourceRecordChangeValue = (Struct) sourceRecordRecordChangeEvent.record().value();

        if (sourceRecordChangeValue != null) {
            Operation operation = Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));

            if (operation != Operation.READ) {

                // Get value changes
                String record = operation == Operation.DELETE ? BEFORE : AFTER;

                Struct struct = (Struct) sourceRecordChangeValue.get(record);
                Map<String, Object> payload = struct.schema().fields().stream()
                        .map(Field::name)
                        .filter(fieldName -> struct.get(fieldName) != null)
                        .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                        .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

                // Get table name
                Struct source = (Struct) sourceRecordChangeValue.get("source");
                String table = (String) source.get("table");


                if (table.equals("Estudante")) {
                    switch (operation) {
                        case CREATE -> estudanteService.create(payload);
                        case UPDATE -> estudanteService.update(payload);
                        case DELETE -> estudanteService.delete();
                    }
                }
            }
        }

        System.out.println("Finalizou");
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
