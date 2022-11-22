package com.abnamro.recipe.request;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.abnamro.recipe.constants.MessageConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class SearchCriteriaRequest {

    @ApiModelProperty(notes = "Name of columns you want to search are " +
            "name, " +
            "servingCapacity, " +
            "recipeType, " +
            "recipeInstructions, " +
            "ingredient", example = "name")
    @Pattern(regexp = "name|servingCapacity|recipeType|recipeInstructions|ingredient", flags = Pattern.Flag.CASE_INSENSITIVE, message = MessageConstants.FILTER_KEY_INVALID)
    private String filterParamKey;

    @ApiModelProperty(notes = "The operation type you wanted to search (cn - Contains, " +
            "nc - Does not Contain, " +
            "eq - Equals, " +
            "ne - Not Equals", example = "cn")
    @Pattern(regexp = "CN|NC|EQ|NE", flags = Pattern.Flag.CASE_INSENSITIVE, message = MessageConstants.FILTER_KEY_INVALID)
    private String operation;

    @ApiModelProperty(hidden = true)
    private String filterOption;
 

    @ApiModelProperty(notes = "Search value", example = "Pasta")
    private Object searchValue;
    
    public SearchCriteriaRequest(String filterParamKey, Object value, String operation) {
        this.filterParamKey = filterParamKey;
        this.searchValue = value;
        this.operation = operation;
    }


}
