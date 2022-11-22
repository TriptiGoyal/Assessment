package com.abnamro.recipe.test.search.filter;

import com.abnamro.recipe.search.SearchOperation;
import com.abnamro.recipe.search.filters.SearchEqual;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EqualFilterSearchTest {

    @Test
    public void test_operationToApplyReturnsFalseWhenOperationIsNotEqual() {
        SearchEqual filterEqual  = new SearchEqual();
        boolean b = filterEqual.operationToApply(SearchOperation.NOT_EQUAL);
        assertFalse(b);
    }


    @Test
    public void test_operationToApplyReturnsFalseWhenOperationIsContain() {
        SearchEqual filterEqual  = new SearchEqual();
        boolean b = filterEqual.operationToApply(SearchOperation.CONTAINS);
        assertFalse(b);
    }

    @Test
    public void test_operationToApplyReturnsFalseWhenOperationIsNull() {
        SearchEqual filterEqual  = new SearchEqual();
        boolean b = filterEqual.operationToApply(null);
        assertFalse(b);
    }


    @Test
    public void test_operationToApplyReturnsTrueWhenOperationIsEqual() {
        SearchEqual filterEqual  = new SearchEqual();
        boolean b = filterEqual.operationToApply(SearchOperation.EQUAL);
        assertTrue(b);

    }

    @Test
    public void test_operationToApplyReturnsFalseWhenOperationIsDoesNotContain() {
        SearchEqual filterEqual  = new SearchEqual();
        boolean b = filterEqual.operationToApply(SearchOperation.DOES_NOT_CONTAIN);
        assertFalse(b);
    }

}