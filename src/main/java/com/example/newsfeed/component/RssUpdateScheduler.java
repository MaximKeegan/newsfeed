package com.example.newsfeed.component;

import com.example.newsfeed.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RssUpdateScheduler {

    private final NewsService newsService;

    @Autowired
    public RssUpdateScheduler(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedRateString = "${app.fetch-interval}")
    public void updateNews() {
        newsService.updateNews();
    }
}
