package com.abnamro.recipe.test.search;

import com.abnamro.recipe.search.SearchOperation;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchOperationTest {


    @Test
    public void test_SearchOperationEnum() {
        Optional<SearchOperation> cn = SearchOperation.getSearchOperation("cn");
        Optional<SearchOperation> nc = SearchOperation.getSearchOperation("nc");
        Optional<SearchOperation> eq = SearchOperation.getSearchOperation("eq");
        Optional<SearchOperation> ne = SearchOperation.getSearchOperation("ne");
        assertTrue(eq.isPresent());
        assertTrue(ne.isPresent());
        assertTrue(cn.isPresent());
        assertTrue(nc.isPresent());
    }
    
    
    @Test
    public void simpleEnumTest() {
        SearchOperation contains = SearchOperation.CONTAINS;
        SearchOperation doesNotContain = SearchOperation.DOES_NOT_CONTAIN;
        SearchOperation equal = SearchOperation.EQUAL;
        SearchOperation notEqual = SearchOperation.NOT_EQUAL;
        assertEquals(SearchOperation.valueOf("CONTAINS"), contains);
        assertEquals(SearchOperation.valueOf("DOES_NOT_CONTAIN"), doesNotContain);
        assertEquals(SearchOperation.valueOf("EQUAL"), equal);
        assertEquals(SearchOperation.valueOf("NOT_EQUAL"), notEqual);
    }
}
