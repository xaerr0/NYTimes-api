package com.codingnomads.nytimesapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Meta {
    private double hits;
    private double offset;
    private double time;
}