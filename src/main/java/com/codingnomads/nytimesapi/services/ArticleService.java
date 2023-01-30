
package com.codingnomads.nytimesapi.services;


import com.codingnomads.nytimesapi.models.Article;
import com.codingnomads.nytimesapi.models.Media;
import com.codingnomads.nytimesapi.models.NytResponse;
import com.codingnomads.nytimesapi.models.Thumbnail;
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
//        List<Article> articlesWithMedia;
//       articlesWithMedia = articles.stream().filter(article -> article.getMedia().isEmpty()).collect(Collectors.toList());
        articles.removeIf(a -> a.getMedia().isEmpty());
        setUrlThumbnail(articles);
        return articles;
    }
    public void setUrlThumbnail(List<Article> articles) {
        for (Article article : articles) {
            List<Media> media = article.getMedia();
            Media thumbnail = media.get(0);
            Thumbnail urlImage = thumbnail.getMediaMetadata().get(0);
            article.setImageUrl(urlImage.getUrl());
        }
    }
}