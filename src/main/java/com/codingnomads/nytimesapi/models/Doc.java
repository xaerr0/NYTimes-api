package com.codingnomads.nytimesapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Doc {
    @JsonProperty("abstract")
    private String summary;

    @JsonProperty("web_url")
    private String webUrl;

    private Multimedia multimedia;
    private Headline headline;
    private Byline byline;

    @JsonProperty("type_of_material")
    private String materialType;

    @JsonProperty("word_count")
    private Double wordCount;

    private String imageUrl;
}