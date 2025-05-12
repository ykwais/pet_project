package ru.mai.lessons.rpks.impl;

import com.typesafe.config.Config;
import ru.mai.lessons.rpks.DbReader;
import ru.mai.lessons.rpks.KafkaReader;
import ru.mai.lessons.rpks.Service;

public class ServiceDeduplication implements Service {
    @Override
    public void start(Config config) {
        DbReader dbReader = new DbReaderImpl(config);
        KafkaReader kafkaReader = new KafkaReaderImpl(config, dbReader);
        kafkaReader.processing();
    }
}
