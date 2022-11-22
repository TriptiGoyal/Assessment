package com.abnamro.recipe.test.search.filter;

import com.abnamro.recipe.search.SearchOperation;
import com.abnamro.recipe.search.filters.SearchDoesNotContain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterSearchNotContainTest {

    @Test
    public void test_operationContain() {
        SearchDoesNotContain filter  = new SearchDoesNotContain();
        boolean b = filter.operationToApply(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void test_whenOperationIsNull() {
        SearchDoesNotContain filter  = new SearchDoesNotContain();
        boolean b = filter.operationToApply(null);
        assertFalse(b);
    }
    
    
    @Test
    public void test_operationEqual() {
        SearchDoesNotContain filter  = new SearchDoesNotContain();
        boolean b = filter.operationToApply(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }

    @Test
    public void test_operationIsEqual() {
        SearchDoesNotContain filter  = new SearchDoesNotContain();
        boolean b = filter.operationToApply(SearchOperation.EQUAL);
        assertFalse(b);

    }

    @Test
    public void test_operationNotContain() {
        SearchDoesNotContain filter  = new SearchDoesNotContain();
        boolean b = filter.operationToApply(SearchOperation.DOES_NOT_CONTAIN);
        assertTrue(b);
    }

}