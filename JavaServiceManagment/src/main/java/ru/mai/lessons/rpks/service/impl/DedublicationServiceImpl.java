package ru.mai.lessons.rpks.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.lessons.rpks.model.Deduplication;
import ru.mai.lessons.rpks.repository.DedublicationRepository;
import ru.mai.lessons.rpks.service.DedublicationService;

import java.util.List;

@Service
@AllArgsConstructor
public class DedublicationServiceImpl implements DedublicationService {

    private final DedublicationRepository ddRepository;

    @Override
    public List<Deduplication> getAllDedublications() {
        return ddRepository.findAll();
    }

    @Override
    public List<Deduplication> getAllDedublicationsByDedublicationId(long dublicationId) {
        return ddRepository.findDeduplicationByDeduplicationId(dublicationId);
    }

    @Override
    public Deduplication findDedublicationByDedublicationIdAndRuleID(long dublicationId, long ruleId) {
        return ddRepository.findDeduplicationByDeduplicationIdAndRuleId(dublicationId, ruleId);
    }

    @Override
    public void deleteAll() {
        ddRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteDedublicationByDedublicationIdAndRuleId(long dublicationId, long ruleId) {
        ddRepository.deleteDeduplicationByDeduplicationIdAndRuleId(dublicationId, ruleId);
    }

    @Override
    public void saveDedublication(Deduplication deduplication) {
        ddRepository.save(deduplication);
    }
}
