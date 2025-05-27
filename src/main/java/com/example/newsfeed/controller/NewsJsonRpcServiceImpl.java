package com.example.newsfeed.controller;

import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import com.example.newsfeed.exception.NewsNotFoundException;
import com.example.newsfeed.repository.FeedRepository;
import com.example.newsfeed.repository.NewsRepository;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AutoJsonRpcServiceImpl
public class NewsJsonRpcServiceImpl implements NewsJsonRpcService {

    private final NewsRepository newsRepository;
    private final FeedRepository feedRepository;

    @Autowired
    public NewsJsonRpcServiceImpl(NewsRepository newsRepository, FeedRepository feedRepository) {
        this.newsRepository = newsRepository;
        this.feedRepository = feedRepository;
    }


    @Override
    public List<NewsWithFeedNameEntity> getNewsList() {
        List<NewsWithFeedNameEntity> news = newsRepository.findTopNewsWithFeedName();
        if (news.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return news;
    }

    @Override
    public List<NewsWithFeedNameEntity> getNewsList(Long feedId) {
        List<NewsWithFeedNameEntity> news = newsRepository.findNewsWithFeedNameByFeedId(feedId);
        if (news.isEmpty()) {
            throw new NewsNotFoundException("News not found with Feed ID: " + feedId);
        }

        return news;
    }

}
