
package com.task.alpha.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Currency {

    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, Double> rates = new HashMap<>();
}
