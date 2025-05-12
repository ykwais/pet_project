package ru.mai.lessons.rpks.controllers.impl;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mai.lessons.rpks.controllers.EnrichmentController;
import ru.mai.lessons.rpks.dto.request.EnrichmentRequest;
import ru.mai.lessons.rpks.dto.response.EnrichmentResponse;
import ru.mai.lessons.rpks.services.EnrichmentService;

@Validated
@RestController
@RequestMapping("/enrichment")
@RequiredArgsConstructor
public class EnrichmentControllerImpl implements EnrichmentController {

  private final EnrichmentService enrichmentService;

  @Override
  @GetMapping("/findAll")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<EnrichmentResponse> getAllEnrichmentRequests() {
    return enrichmentService.getAllEnrichmentRequests();
  }

  @Override
  @GetMapping("/findAll/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<EnrichmentResponse> getAllEnrichmentRequestsByEnrichmentRequestId(
      @PathVariable("id") long id) {
    return enrichmentService.getAllEnrichmentRequestsByEnrichmentRequestId(id);
  }

  @Override
  @GetMapping("/find/{enrichmentId}/{ruleId}")
  @ResponseStatus(value = HttpStatus.OK)
  public EnrichmentResponse getEnrichmentRequestById(
      @PathVariable("enrichmentId") long enrichmentId,
      @PathVariable("ruleId") long ruleId) {
    return enrichmentService.getEnrichmentRequestById(enrichmentId, ruleId);
  }

  @Override
  @DeleteMapping("/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteEnrichmentRequest() {
    enrichmentService.deleteEnrichmentRequest();
  }

  @Override
  @DeleteMapping("/delete/{enrichmentId}/{ruleId}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteEnrichmentRequestById(
      @PathVariable("enrichmentId") long enrichmentId,
      @PathVariable("ruleId") long ruleId) {
    enrichmentService.deleteEnrichmentRequestById(enrichmentId, ruleId);
  }

  @Override
  @PostMapping("/save")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void save(@RequestBody @Valid EnrichmentRequest enrichment) {
    enrichmentService.save(enrichment);
  }
}
