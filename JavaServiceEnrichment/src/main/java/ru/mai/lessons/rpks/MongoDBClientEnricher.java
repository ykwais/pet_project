package ru.mai.lessons.rpks;


import com.fasterxml.jackson.databind.JsonNode;
import ru.mai.lessons.rpks.model.Quatrifollio;


import java.util.Map;

public interface MongoDBClientEnricher {
    void enrichMessage(JsonNode messageInJson, Map<String, Quatrifollio<Long, String, String, String>> map);
}
