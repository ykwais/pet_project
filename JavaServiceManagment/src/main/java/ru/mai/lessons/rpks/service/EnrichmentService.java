package ru.mai.lessons.rpks.service;

import ru.mai.lessons.rpks.model.Enrichment;

import java.util.List;

public interface EnrichmentService {
    List<Enrichment> getAll();
    List<Enrichment> getEnrichmentsByEnrichmentId(long enrichmentId);
    Enrichment getEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId);
    void deleteAll();
    void deleteEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId);
    Enrichment saveEnrichment(Enrichment enrichment);
}
