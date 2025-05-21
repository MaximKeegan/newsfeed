package com.example.newsfeed.controller;

import com.example.newsfeed.repository.FeedRepository;
import com.example.newsfeed.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.newsfeed.entity.FeedEntity;

@Controller
public class NewsController {
    private final NewsRepository newsRepository;
    private final FeedRepository feedRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository, FeedRepository feedRepository) {
        this.newsRepository = newsRepository;
        this.feedRepository = feedRepository;
    }

    @GetMapping("/news")
    public String news(Model model) {
        Iterable<FeedEntity> feeds = feedRepository.findAll();
        model.addAttribute("feeds", feeds);
        return "news";
    }
}