package com.abnamro.recipe.search;

import java.util.Optional;

public enum FilterOption {
    ANY, ALL;

    public static Optional<FilterOption> getFilterOptions(final String filterOption) {
        String lowerCaseFilter = filterOption.toLowerCase();
        switch (lowerCaseFilter) {
            case "all":
                return Optional.of(ALL);
            case "any":
                return Optional.of(ANY);
            default :
                return Optional.of(ALL);

        }
    }
}
