package ru.mai.lessons.rpks.impl;

import com.typesafe.config.Config;
import ru.mai.lessons.rpks.DbReader;
import ru.mai.lessons.rpks.KafkaReader;
import ru.mai.lessons.rpks.Service;

public class ServiceEnrichment implements Service {
    @Override
    public void start(Config config) {
        DbReader dbReader = new EnrichDBReaderImpl(config);
        KafkaReader kafkaReader = new EnrichKafkaReaderImpl(config, dbReader);
        kafkaReader.processing();
    }
}
