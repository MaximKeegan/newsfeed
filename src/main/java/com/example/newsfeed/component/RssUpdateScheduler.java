package com.example.newsfeed.component;

import com.example.newsfeed.service.NewsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RssUpdateScheduler {

    private final NewsService newsService;

    public RssUpdateScheduler(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedRate = 10000) // Каждые 60 минут
    public void updateNews() {
        newsService.updateNews();
    }
}
