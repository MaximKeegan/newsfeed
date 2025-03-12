package com.example.newsfeed.service;

import com.example.newsfeed.model.News;
import com.example.newsfeed.repositor.FeedRepository;
import com.example.newsfeed.repositor.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final RssParserService rssParser;
    private final NewsRepository newsRepository;
    private final FeedRepository feedRepository;

    @Autowired
    public NewsService(RssParserService rssParser, NewsRepository newsRepository, FeedRepository feedRepository) {
        this.rssParser = rssParser;
        this.newsRepository = newsRepository;
        this.feedRepository = feedRepository;
    }

    public void updateNews() {
        var feed = feedRepository.findTopEnabledOrderByLastFetchedAscending();

        feed.setLastFetched(new Timestamp(System.currentTimeMillis()));
        feedRepository.save(feed);

        try {
            List<News> newsList = rssParser.parse(feed);
            if (newsList.isEmpty()) {
                return;
            }
            List<String> guids = newsList.stream().map(News::getGuid).collect(Collectors.toList());
            List<String> existingGuids = newsRepository.findByGuidIn(guids)
                    .stream()
                    .map(News::getGuid)
                    .collect(Collectors.toList());

            List<News> newNews = newsList.stream()
                    .filter(news -> !existingGuids.contains(news.getGuid()))
                    .collect(Collectors.toList());

            newsRepository.saveAll(newNews);
            System.out.println("Добавлено новых новостей: " + newNews.size());
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении новостей: " + e.getMessage());
        }
    }

}
