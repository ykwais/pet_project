package ru.mai.lessons.rpks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mai.lessons.rpks.model.Enrichment;

import java.util.List;

public interface EnrichmentRepository extends JpaRepository<Enrichment, Long> {
    List<Enrichment> getEnrichmentByEnrichmentId(long enrichmentId);
    Enrichment getEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId);
    void deleteEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId);
}
