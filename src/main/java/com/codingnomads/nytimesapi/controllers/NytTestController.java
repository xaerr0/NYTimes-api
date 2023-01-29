package com.codingnomads.nytimesapi.controllers;

import com.codingnomads.nytimesapi.models.Article;
import com.codingnomads.nytimesapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NytTestController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/nyc/test")
    public List<Article> getMostPopular() {
        return articleService.getMostPopular();
    }
}