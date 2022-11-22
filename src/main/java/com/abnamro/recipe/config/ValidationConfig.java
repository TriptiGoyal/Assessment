package com.abnamro.recipe.config;

public class ValidationConfig {
	
	private ValidationConfig() {
		
	}

    /**
     * PATTERN will match name of ingredient and recipe
     */
    public static final String RECIPE_INGREDIENT_NAME_PATTERN = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";

    /**
     * Free text pattern will look for matching Ingredient Quantity and Recipe instructions
     */
    public static final String TEXT_FREE_PATTERN = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$";

    
    /**
     * Maximum length of ingredient and recipe name
     */
    public static final int MAXIMUM_LENGTH_NAME = 100;

    /**
     * Maximum Default length of ingredient and recipe name
     */
    public static final int MAXIMUM_LENGTH_DEFAULT = 255;


}
