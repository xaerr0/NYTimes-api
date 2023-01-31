package com.codingnomads.nytimesapi.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NytSearchResponse {

    private String status;
    private String copyright;
    private Response response;
}