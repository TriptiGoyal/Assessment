package com.abnamro.recipe.test.search;

import com.abnamro.recipe.search.FilterOption;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterOptionTest {

    @Test
    public void filterOptionClassTest() {
        FilterOption option1 = FilterOption.ANY;
        FilterOption option2 = FilterOption.ALL;
        assertEquals(FilterOption.valueOf("ALL"), option2);
        assertEquals(FilterOption.valueOf("ANY"), option1);
    }

    @Test
    public void fetchEnum() {
        Optional<FilterOption> all = FilterOption.getFilterOptions("all");
        Optional<FilterOption> any = FilterOption.getFilterOptions("any");
        assertTrue(all.isPresent());
        assertEquals(FilterOption.ALL, all.get());
        assertEquals(FilterOption.ANY, any.get());
    }
}
