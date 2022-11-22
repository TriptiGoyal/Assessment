package com.abnamro.recipe.test.search;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.RecipeCriteriaSpecificationsBuilder;
import com.abnamro.recipe.search.SearchCriteria;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecipeSpecificationBuilderTest {
    List<SearchCriteria> params;

    public RecipeSpecificationBuilderTest() {
        params = new ArrayList<>();
    }

    @Test
    public void test_buildMethodEmptyParameter_successfully() {
        RecipeCriteriaSpecificationsBuilder builder = new RecipeCriteriaSpecificationsBuilder(params);
        Optional<Specification<Recipe>> build = builder.build();
        assertEquals(Optional.empty(), build);
    }

    @Test
    public void test_buildMethodWhenParamsIsNotEmpty_successfully() {
        RecipeCriteriaSpecificationsBuilder builder = new RecipeCriteriaSpecificationsBuilder(params);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFilterOption("any");
        searchCriteria.setFilterKey("name");
        searchCriteria.setOperation("cn");
        searchCriteria.setValue("pasta");
        params.add(searchCriteria);
        Optional<Specification<Recipe>> build = builder.build();
        assertTrue(build.isPresent());
     }

}
