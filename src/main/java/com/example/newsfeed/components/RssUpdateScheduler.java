package com.example.newsfeed.components;

import com.example.newsfeed.services.NewsService;
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
        String rssUrl = "https://www.theverge.com/rss/index.xml";
        newsService.updateNews(rssUrl);
    }
}
