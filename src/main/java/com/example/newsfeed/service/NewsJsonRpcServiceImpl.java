package com.example.newsfeed.service;

import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import com.example.newsfeed.repository.FeedRepository;
import com.example.newsfeed.repository.NewsRepository;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

//@JsonRpcService("/api/news")
//@AutoJsonRpcServiceImpl
public class NewsJsonRpcServiceImpl implements NewsJsonRpcService {

//    private final NewsRepository newsRepository;
//    private final FeedRepository feedRepository;

//    @Autowired
//    public NewsJsonRpcServiceImpl(NewsRepository newsRepository, FeedRepository feedRepository) {
//        this.newsRepository = newsRepository;
//        this.feedRepository = feedRepository;
//    }


    @Override
    public List<String> getNewsList() {

//        List<NewsWithFeedNameEntity> news = newsRepository.findTopNewsWithFeedName();
//        if (news.isEmpty()) {
//            throw new RuntimeException();
//        }

        List newsList = new ArrayList();
        newsList.add("test");
        newsList.add("test1");
        return newsList;
    }
}
