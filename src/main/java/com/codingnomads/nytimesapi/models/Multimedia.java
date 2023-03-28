package com.codingnomads.nytimesapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Multimedia {
    private String subType;
    private String url;
    private Double height;
    private Double width;
}