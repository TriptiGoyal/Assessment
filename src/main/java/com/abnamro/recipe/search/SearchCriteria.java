package com.abnamro.recipe.search;

import com.abnamro.recipe.request.SearchCriteriaRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	private String filterKey;
	private String filterOption;
	private Object value;
	private String operation;

	public SearchCriteria() {
	}

	public SearchCriteria(String filterKey, String operation, Object value) {
		super();
		this.filterKey = filterKey;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(SearchCriteriaRequest request) {
		this.filterOption = request.getFilterOption();
		this.filterKey = request.getFilterParamKey();
		this.operation = request.getOperation();
		this.value = request.getSearchValue();
	}

}
