package com.abnamro.recipe.search.filters;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.abnamro.recipe.config.DBConfig.KEY;

public class SearchEqual implements SearchFilter {

    @Override
    public boolean operationToApply(SearchOperation opt) {
        return opt == SearchOperation.EQUAL;
    }

    @Override
    public Predicate apply(Root<Recipe> root, Join<Object, Object> subRoot,CriteriaBuilder cb, String filterKey, String filterValue) {
        if (filterKey.equals(KEY))
            return cb.equal(cb.lower(subRoot.get(filterKey).as(String.class)), filterValue);

        return cb.equal(cb.lower(root.get(filterKey).as(String.class)), filterValue);
    }
}
