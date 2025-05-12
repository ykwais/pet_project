package ru.mai.lessons.rpks.service;

import ru.mai.lessons.rpks.model.Deduplication;

import java.util.List;

public interface DedublicationService {
    List<Deduplication> getAllDedublications();
    List<Deduplication> getAllDedublicationsByDedublicationId(long dublicationId);
    Deduplication findDedublicationByDedublicationIdAndRuleID(long dublicationId, long ruleId);
    void deleteAll();
    void deleteDedublicationByDedublicationIdAndRuleId(long dublicationId, long ruleId);
    void saveDedublication(Deduplication deduplication);
}
