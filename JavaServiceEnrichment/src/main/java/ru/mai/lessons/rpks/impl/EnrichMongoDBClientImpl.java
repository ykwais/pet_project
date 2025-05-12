package ru.mai.lessons.rpks.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import ru.mai.lessons.rpks.MongoDBClientEnricher;
import ru.mai.lessons.rpks.model.Quatrifollio;


import java.util.Map;

@Slf4j
public class EnrichMongoDBClientImpl implements MongoDBClientEnricher {


    private final MongoClient mongoClient;

    private final MongoDatabase database;

    private MongoCollection<Document> collection;

    private final String nameCollection;


    public EnrichMongoDBClientImpl(Config config) {

        this.mongoClient = MongoClients.create(config.getString("mongo.connectionString"));
        this.database = mongoClient.getDatabase(config.getString("mongo.database"));
        this.nameCollection = config.getString("mongo.collection");
        this.collection = database.getCollection(nameCollection);

    }

    @Override
    public void enrichMessage(JsonNode messageInJson, Map<String, Quatrifollio<Long, String, String, String>> map) {

        log.info("start enriching message");

        collection = database.getCollection(nameCollection);

        for (Map.Entry<String, Quatrifollio<Long, String, String, String>> entry : map.entrySet()) {
            String fieldNameMongo = entry.getValue().fieldNameInMongo();
            String fieldValueMongo = entry.getValue().fieldValueInMongo();
            String fieldInMessage = entry.getKey();
            String defaultValue = entry.getValue().valueDefault();


            Document doc = collection.find(Filters.eq(fieldNameMongo, fieldValueMongo)).sort(Sorts.descending("_id")).first();

                if (doc != null) {

                    ObjectMapper objectMapper = new ObjectMapper();

                    ObjectNode documentNode = objectMapper.valueToTree(doc);

                    documentNode.remove("_id");

                    ObjectNode oidNode = objectMapper.createObjectNode();
                    oidNode.put("$oid", doc.get("_id").toString());

                    documentNode.set("_id", oidNode);

                    ((ObjectNode) messageInJson).set(fieldInMessage, documentNode);

                } else {
                    ((ObjectNode) messageInJson).put(fieldInMessage, defaultValue);
                }
        }
    }
}
