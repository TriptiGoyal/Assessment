package com.abnamro.recipe.search;

import com.abnamro.recipe.models.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class RecipeCriteriaSpecificationsBuilder {
    private final List<SearchCriteria> searchCriteriaParams;

    public RecipeCriteriaSpecificationsBuilder(List<SearchCriteria> searchCriterionRequests) {
        this.searchCriteriaParams = searchCriterionRequests;
    }

    public final RecipeCriteriaSpecificationsBuilder with(SearchCriteria searchCriteriaRequest) {
        searchCriteriaParams.add(searchCriteriaRequest);
        return this;
    }

    public Optional<Specification<Recipe>> build() {
        if (searchCriteriaParams.size() == 0) return Optional.empty();
        Specification<Recipe> recipeSpec = new RecipeCriteriaSpecifications(searchCriteriaParams.get(0));
        for (int i = 1; i < searchCriteriaParams.size(); i++) {
            SearchCriteria criteria = searchCriteriaParams.get(i);
            Optional<FilterOption> filterOption = FilterOption.getFilterOptions(criteria.getFilterOption());
            if (filterOption.isPresent()) {
                recipeSpec = (filterOption.get() == FilterOption.ALL)
                        ? Specification.where(recipeSpec).and(new RecipeCriteriaSpecifications(criteria))
                        : Specification.where(recipeSpec).or(new RecipeCriteriaSpecifications(criteria));
            }
        }
        return Optional.of(recipeSpec);
    }
}
