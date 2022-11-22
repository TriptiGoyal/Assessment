package com.abnamro.recipe.constants;

public class MessageConstants {

	private MessageConstants(){
		
	}
	
	public static final String INGREDIENT_NOT_FOUND="Requested ingredient not found";
	public static final String INGREDIENT_NOT_BLANK="Ingredient name cannot be blank";
	public static final String INGREDIENT_MAX_SIZE="Size of ingredient should be of {max} characters.";
	public static final String INGREDIENT_PATTERN="The ingredient name should contain only letters and the following characters: ',.- and space.";
	public static final String NULL_ID="Please provide a valid ID.";
	public static final String NEGATIVE_ID="Please provide a valid ID";
	public static final String BLANK_RECIPE_NAME="Please provide a valid Recipe name";
	public static final String MAX_RECIPE_NAME="Maximum length of Recipe Name should be of {max} characters.";
	public static final String RECIPE_NAME_PATTERN="Recipe name should contain only letters and the following characters: ',.- and space.";
	public static final String RECIPE_TYPE_INVALID="Recipe Type is not valid";
	public static final String FILTER_OPTION_INVALID="Filter Option is not valid";
	public static final String SEARCH_OPERATION="Search operation is not valid";
	public static final String FILTER_KEY_INVALID="Filter Key is not valid";
	public static final String NEGATIVE_SERVING_CAPACITY="Please provide the valid number of servings.";
	public static final String NULL_SERVING_CAPACITY="Serving Capacity can't be null";
	public static final String RECIPE_INSTRUCTIONS_BLANK="Recipe instructions cannot be blank.";
	public static final String RECIPE_MAX_INSTRUCTIONS_SIZE="Recipe instructions can be {max} characters long at maximum.";
	public static final String VALID_INSTRUCTION_PATTERN="Please provide a valid instruction. Avoid using special characters, except: \\ / - + . , ? ! * ( ) ; ";
	public static final String RECIPE_NOT_FOUND="Requested recipe not found";
	public static final String INVALID_JSON_FORMAT="Invalid request. Please make sure you have provided all information correctly.";
	public static final String INTERNAL_SERVER_ERROR="Internal server error has occurred.";
	public static final String UNABLE_TO_DELETE="Uniqueness of the entity is required or if you wanted to delete the item that cannot be deleted. Please check if it has dependent entities.";
	public static final String SEARCH_CRITERIA_NOT_FOUND="No criteria has been found.";
	public static final String INGREDIENT_QUANTITY_IS_BLANK="Ingredient quantity is blank.";
	public static final String VALID_QUANTITY_PATTERN="Please provide a valid quantity for recipe instruction. Avoid using special characters, except: \\ / - + . , ? ! * ( ) ; ";



	
}
