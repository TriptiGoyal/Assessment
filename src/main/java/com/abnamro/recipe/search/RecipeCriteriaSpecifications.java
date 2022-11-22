package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.filters.*;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.abnamro.recipe.config.DBConfig.TABLE_NAME_JOINING;

public class RecipeCriteriaSpecifications implements Specification<Recipe> {
    private final SearchCriteria searchCriteria;

    private static final List<SearchFilter> searchFilters = new ArrayList<>();
    private void filterList() {
        searchFilters.add(new SearchEqual());
        searchFilters.add(new SearchNotEqual());
        searchFilters.add(new SearchContains());
        searchFilters.add(new SearchDoesNotContain());
    }
    
    public RecipeCriteriaSpecifications(SearchCriteria criteria) {
        super();
        filterList();
        this.searchCriteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Optional<SearchOperation> operation = SearchOperation.getSearchOperation(searchCriteria.getOperation());
        String filterValue = searchCriteria.getValue().toString().toLowerCase();
        String filterKey = searchCriteria.getFilterKey();
        Join<Object, Object> subRoot = root.join(TABLE_NAME_JOINING, JoinType.INNER);
        query.distinct(true);
        return operation.flatMap(searchOperation -> searchFilters
                .stream()
                .filter(searchFilter -> searchFilter.operationToApply(searchOperation))
                .findFirst()
                .map(searchFilter -> searchFilter.apply(root, subRoot, cb, filterKey, filterValue))).orElse(null);
    }


}
