package ru.mai.lessons.rpks.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import ru.mai.lessons.rpks.repository.DedublicationRepository;
import ru.mai.lessons.rpks.repository.EnrichmentRepository;
import ru.mai.lessons.rpks.repository.FilterRepository;

@Component
@AllArgsConstructor
public class MetricsService implements InfoContributor {

    private final FilterRepository filterRepository;
    private final DedublicationRepository deduplicationRepository;
    private final EnrichmentRepository enrichmentRepository;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("countFilters", filterRepository.count())
                .withDetail("countDeduplications", deduplicationRepository.count())
                .withDetail("countEnrichments", enrichmentRepository.count());
    }

}
