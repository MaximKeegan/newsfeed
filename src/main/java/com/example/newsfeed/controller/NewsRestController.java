package com.example.newsfeed.controller;

import com.example.newsfeed.entity.JsonResponse;
import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import com.example.newsfeed.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restapi/news")
public class NewsRestController {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsRestController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<?> getNews() {
        List<NewsWithFeedNameEntity> news = newsRepository.findTopNewsWithFeedName();
        if (news.isEmpty()) {
            JsonResponse error = new JsonResponse("News not found", "News not found");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        List<NewsWithFeedNameEntity> news = newsRepository.findNewsWithFeedNameByFeedId(id);
        if (news.isEmpty()) {
            JsonResponse error = new JsonResponse("News not found", "News not found with Feed ID: " + id);
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(news);
    }

}
