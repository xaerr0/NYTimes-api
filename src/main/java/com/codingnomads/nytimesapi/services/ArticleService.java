package com.codingnomads.nytimesapi.services;


import com.codingnomads.nytimesapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleService {

    @Autowired
    RestTemplate restTemplate;
    @Value("${api_key}")
    private String apikey;
    @Value("${mostPopularUrl}")
    private String mostPopularUrl;
    @Value("${searchUrl}")
    private String searchUrl;

    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(mostPopularUrl + "api-key=" + apikey, NytResponse.class);
        List<Article> results = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            return response.getResults();
        } else {
            return results;
        }
    }

    public List<Article> checkForMedia(List<Article> articles) {
//        List<Article> articlesWithMedia;
//       articlesWithMedia = articles.stream().filter(article -> article.getMedia().isEmpty()).collect(Collectors.toList());
        articles.removeIf(a -> a.getMedia().isEmpty());
        setUrlThumbnail(articles);
        return articles;
    }

    //TODO Combine these 2 methods into 1?
    public void setUrlThumbnail(List<Article> articles) {
        for (Article article : articles) {
            List<Media> media = article.getMedia();
            Media thumbnail = media.get(0);
            Thumbnail urlImage = thumbnail.getMediaMetadata().get(2);
            article.setImageUrl(urlImage.getUrl());
        }
    }


//    public List<Doc> getSearchResults(String searchText) {
//        ResponseEntity<NytSearchResponse> response = restTemplate.getForEntity(searchUrl + searchText + "&api-key=" +
//                                                                                     apikey, NytSearchResponse.class);
//        List<Doc> docs = new ArrayList<>();
//        if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.OK)){
//            return docs;
//        } else {
//            System.out.println("Error!!");
//        }
//        return docs;
//    }

    public List<Doc> getSearchResults(String searchText) {
        ResponseEntity<NytSearchResponse> response = restTemplate
                .getForEntity(searchUrl + searchText + "?api-key=" + apikey, NytSearchResponse.class);
        List<Doc> results = new ArrayList<>();
        if (response != null && response.getStatusCode().equals("OK")) {

            return results;
        }
        return null;
    }

}