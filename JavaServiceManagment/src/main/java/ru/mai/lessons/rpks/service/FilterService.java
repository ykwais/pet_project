package ru.mai.lessons.rpks.service;

import ru.mai.lessons.rpks.model.Filter;

import java.util.List;

public interface FilterService {
    List<Filter> getAllFilters();
    List<Filter> getAllFiltersByFilterId(long filterId);
    Filter getFilterByFilterIdAndRuleId(long filterId, long ruleId);
    void deleteAllFilters();
    void deleteFilterByFilterIdAndRuleID(long filterId, long ruleId);
    Filter saveFilter(Filter filter);
}
