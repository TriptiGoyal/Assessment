package com.abnamro.recipe.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.abnamro.recipe.constants.MessageConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class SearchRecipeRequest {
    @JsonProperty("criteria")
    @ApiModelProperty(notes = "Add Criteria you want to search recipe with")
    @Valid
    private List<SearchCriteriaRequest> searchRequests;

    @ApiModelProperty(notes = "You can choose all or just one", example = "all")
    @Pattern(regexp = "ANY|ALL", flags = Pattern.Flag.CASE_INSENSITIVE, message = MessageConstants.FILTER_OPTION_INVALID)
    private String filterOption;
}
