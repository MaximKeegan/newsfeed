package com.example.newsfeed.service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AutoJsonRpcServiceImpl
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

        List<String> newsList = new ArrayList<>();
        newsList.add("test");
        newsList.add("test1");
        return newsList;
    }
}
