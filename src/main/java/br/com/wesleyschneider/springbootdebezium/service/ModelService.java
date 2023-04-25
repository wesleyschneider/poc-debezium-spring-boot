package br.com.wesleyschneider.springbootdebezium.service;

import io.debezium.data.Envelope;

import java.util.Map;

public interface ModelService {
    void execute(Map<String, Object> payload, Envelope.Operation operation);
    void create(Map<String, Object> payload);
    void update(Map<String, Object> payload);
    void delete(Map<String, Object> payload);
}
