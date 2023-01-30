package com.codingnomads.nytimesapi.controllers;

import com.codingnomads.nytimesapi.models.Article;
import com.codingnomads.nytimesapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        List<Article> articles = articleService.getMostPopular();
        model.addAttribute("articleList", articleService.checkForMedia(articles));
        return "index";
    }
}