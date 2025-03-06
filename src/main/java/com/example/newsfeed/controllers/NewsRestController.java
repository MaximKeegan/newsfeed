package com.example.newsfeed.controllers;

import com.example.newsfeed.models.JsonResponse;
import com.example.newsfeed.models.News;
import com.example.newsfeed.models.NewsWithFeedName;
import com.example.newsfeed.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsRestController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<?> getNews() {
        List<NewsWithFeedName> news = newsRepository.findTopNewsWithFeedName();
        if (news.isEmpty()) {
            JsonResponse error = new JsonResponse("News not found", "News not found");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        List<NewsWithFeedName> news = newsRepository.findNewsWithFeedNameByFeedId(id);
        if (news.isEmpty()) {
            JsonResponse error = new JsonResponse("News not found", "News not found with Feed ID: " + id);
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(news);
    }

}
