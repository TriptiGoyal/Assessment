package com.abnamro.recipe.search.filters;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.*;

import static com.abnamro.recipe.config.DBConfig.KEY;

public class SearchContains implements SearchFilter {

	@Override
	public boolean operationToApply(SearchOperation opt) {
		return opt == SearchOperation.CONTAINS;
	}

	@Override
	public Predicate apply(Root<Recipe> root, Join<Object, Object> subRoot, CriteriaBuilder criteriaBuilder,
			String filterKey, String filterValue) {
		if (filterKey.equals(KEY))
			return criteriaBuilder.like(criteriaBuilder.lower(subRoot.get(filterKey).as(String.class)),
					"%" + filterValue + "%");

		return criteriaBuilder.like(criteriaBuilder.lower(root.get(filterKey).as(String.class)),
				"%" + filterValue + "%");
	}
}
