package com.abnamro.recipe.search.filters;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchFilter  {
    boolean operationToApply(SearchOperation opt);
    Predicate apply(Root<Recipe> root, Join<Object, Object> subRoot,CriteriaBuilder criteriaBuilder, String filterKey, String filterValue);
}