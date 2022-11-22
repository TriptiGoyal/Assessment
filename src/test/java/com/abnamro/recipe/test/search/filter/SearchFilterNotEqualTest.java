package com.abnamro.recipe.test.search.filter;

import com.abnamro.recipe.search.SearchOperation;
import com.abnamro.recipe.search.filters.SearchNotEqual;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchFilterNotEqualTest {

    @Test
    public void test_operationNotEqual() {
        SearchNotEqual filterNotEqual  = new SearchNotEqual();
        boolean b = filterNotEqual.operationToApply(SearchOperation.NOT_EQUAL);
        assertTrue(b);
    }

    @Test
    public void test_operationIsEqual() {
        SearchNotEqual filterNotEqual  = new SearchNotEqual();
        boolean b = filterNotEqual.operationToApply(SearchOperation.EQUAL);
        assertFalse(b);
    }

    @Test
    public void test_operationDoesNotContain() {
        SearchNotEqual filterNotEqual  = new SearchNotEqual();
        boolean b = filterNotEqual.operationToApply(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

    @Test
    public void test_operationIsContain() {
        SearchNotEqual filterNotEqual  = new SearchNotEqual();
        boolean b = filterNotEqual.operationToApply(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void test_operationIsNull() {
        SearchNotEqual filterNotEqual  = new SearchNotEqual();
        boolean b = filterNotEqual.operationToApply(null);
        assertFalse(b);
    }

    @Test
    public void apply() {
    }
}