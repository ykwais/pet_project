package ru.mai.lessons.rpks.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.lessons.rpks.model.Enrichment;
import ru.mai.lessons.rpks.repository.EnrichmentRepository;
import ru.mai.lessons.rpks.service.EnrichmentService;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrichmentServiceImpl implements EnrichmentService {
    private final EnrichmentRepository enrichmentRepository;


    @Override
    public List<Enrichment> getAll() {
        return enrichmentRepository.findAll();
    }

    @Override
    public List<Enrichment> getEnrichmentsByEnrichmentId(long enrichmentId) {
        return enrichmentRepository.getEnrichmentByEnrichmentId(enrichmentId);
    }

    @Override
    public Enrichment getEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId) {
        return enrichmentRepository.getEnrichmentByEnrichmentIdAndRuleId(enrichmentId, ruleId);
    }

    @Override
    public void deleteAll() {
        enrichmentRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteEnrichmentByEnrichmentIdAndRuleId(long enrichmentId, long ruleId) {
        enrichmentRepository.deleteEnrichmentByEnrichmentIdAndRuleId(enrichmentId, ruleId);
    }

    @Override
    public Enrichment saveEnrichment(Enrichment enrichment) {
        return enrichmentRepository.save(enrichment);
    }
}
