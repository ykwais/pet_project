package ru.mai.lessons.rpks.impl;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import ru.mai.lessons.rpks.DbReader;
import ru.mai.lessons.rpks.KafkaReader;
import ru.mai.lessons.rpks.KafkaWriter;
import ru.mai.lessons.rpks.RuleProcessor;
import ru.mai.lessons.rpks.model.Message;
import ru.mai.lessons.rpks.model.Rule;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
public class EnrichKafkaReaderImpl implements KafkaReader {

//    static final String TOPIC_IN = "test_topic_in";
//
//    private final KafkaConsumer<String, String> consumer;
//
//    private final KafkaWriter producer;
//
//    private final RuleProcessor processor;
//
//    private final DbReader dbReader;
//
//    public EnrichKafkaReaderImpl(Config config, DbReader dbReader) {
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString("kafka.consumer.bootstrap.servers"));
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getString("kafka.consumer.group.id"));
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getString("kafka.consumer.auto.offset.reset"));
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//
//        this.consumer = new KafkaConsumer<>(props);
//        this.consumer.subscribe(Collections.singletonList(TOPIC_IN));
//        this.dbReader = dbReader;
//        this.producer = new EnrichKafkaWriterImpl(config);
//        this.processor = new EnrichRuleProcessorImpl(config);
//    }

    static final String KAFKA_CONSUMER_CONFIG = "kafka.consumer";

    private final KafkaConsumer<String, String> consumer;

    private final KafkaWriter producer;

    private final RuleProcessor processor;

    private final DbReader dbReader;

    public EnrichKafkaReaderImpl(Config config, DbReader dbReader) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString(KAFKA_CONSUMER_CONFIG + ".bootstrap.servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getString(KAFKA_CONSUMER_CONFIG + ".group.id"));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getString(KAFKA_CONSUMER_CONFIG + ".auto.offset.reset"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        List<String> topics = Arrays.asList(config.getStringList(KAFKA_CONSUMER_CONFIG + ".topics").toArray(new String[0]));
        log.info("Topics!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: {}", topics);

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(topics);
        this.dbReader = dbReader;
        this.producer = new EnrichKafkaWriterImpl(config);
        this.processor = new EnrichRuleProcessorImpl(config);
    }

    @Override
    public void processing() {
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            log.info("count:{}", records.count());

            for (ConsumerRecord<String, String> oneRecord : records) {
                Rule[] currentRules = dbReader.readRulesFromDB();
                log.info("rule reading");

                Message afterEnrichMessage = processor.processing(new Message(oneRecord.value()), currentRules);

                if (afterEnrichMessage != null) {
                    log.info("message after enrichment{}", afterEnrichMessage);
                    producer.processing(afterEnrichMessage);
                }
            }
        }
    }
}
