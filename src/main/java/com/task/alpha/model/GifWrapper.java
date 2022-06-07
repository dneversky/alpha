package com.task.alpha.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GifWrapper {

    @JsonProperty("data")
    private List<Gif> data = new ArrayList<>();

    @Override
    public String toString() {
        return "GifWrapper{" +
                "data=" + data +
                '}';
    }
}