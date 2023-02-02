package com.codingnomads.nytimesapi.controllers;

import com.codingnomads.nytimesapi.models.Article;
import com.codingnomads.nytimesapi.models.Doc;
import com.codingnomads.nytimesapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/search")
    public String displaySearchPage(Model model) {
        List<Article> articles = articleService.getMostEmailed();
        model.addAttribute("articleList", articleService.checkForMedia(articles));
        return "search";
    }

    @PostMapping("/search")
    public String searchResults(Model model, @RequestParam(value = "searchText") String searchText) {
        model.addAttribute("searchResults", articleService.getSearchResults(searchText));
        return "search-results";
    }
}