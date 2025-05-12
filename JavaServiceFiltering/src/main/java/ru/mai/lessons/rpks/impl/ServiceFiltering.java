package ru.mai.lessons.rpks.impl;

import com.typesafe.config.Config;
import ru.mai.lessons.rpks.DbReader;
import ru.mai.lessons.rpks.KafkaReader;
import ru.mai.lessons.rpks.Service;

public class ServiceFiltering implements Service {
    @Override
    public void start(Config config) {
        DbReader dbReader = new DbReaderFiltering(config);

        KafkaReader kafkaReader = new KafkaReaderFiltering(config, dbReader);

        kafkaReader.processing();
    }
}
