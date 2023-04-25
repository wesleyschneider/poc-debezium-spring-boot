package br.com.wesleyschneider.springbootdebezium.service;


import io.debezium.data.Envelope;

import java.util.Map;

public abstract class BaseService implements ModelService {
    @Override
    public void execute(Map<String, Object> payload, Envelope.Operation operation) {
        switch(operation){
            case CREATE -> this.create(payload);
            case UPDATE -> this.update(payload);
            case DELETE -> this.delete(payload);
        }
    }
}
