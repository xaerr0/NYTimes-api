
package com.codingnomads.nytimesapi.services;


import com.codingnomads.nytimesapi.models.Article;
import com.codingnomads.nytimesapi.models.Media;
import com.codingnomads.nytimesapi.models.NytResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Value("${api_key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Autowired
    RestTemplate restTemplate;

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
        List<Article> articlesWithMedia = new ArrayList<>();
       articlesWithMedia = articles.stream().filter(article -> article.getMedia().isEmpty()).collect(Collectors.toList());
//        articles.removeIf(a -> a.getMedia().isEmpty());


        return articlesWithMedia;

    }
}