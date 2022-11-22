package com.abnamro.recipe.test.search.filter;

import com.abnamro.recipe.search.SearchOperation;
import com.abnamro.recipe.search.filters.SearchContains;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsFilterSearchTest {

    @Test
    public void test_operationEqual() {
        SearchContains filter  = new SearchContains();
        boolean b = filter.operationToApply(SearchOperation.EQUAL);
        assertFalse(b);

    }

    @Test
    public void test_operationDoesNotContains() {
        SearchContains filter  = new SearchContains();
        boolean b = filter.operationToApply(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

    @Test
    public void test_operationContains() {
        SearchContains filter  = new SearchContains();
        boolean b = filter.operationToApply(SearchOperation.CONTAINS);
        assertTrue(b);
    }
	
	
    @Test
    public void test_operationNotEqual() {
        SearchContains filter  = new SearchContains();
        boolean b = filter.operationToApply(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }

    @Test
    public void test_operationNull() {
        SearchContains filter  = new SearchContains();
        boolean b = filter.operationToApply(null);
        assertFalse(b);
    }

}