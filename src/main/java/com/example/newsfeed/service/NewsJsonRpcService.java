package com.example.newsfeed.service;

import com.example.newsfeed.entity.NewsEntity;
import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.stereotype.Service;
//import com.youkol.support.jsonrpc4j.server.JsonRpcMultiServiceName;

import java.util.List;

//@JsonRpcService("news")
//@JsonRpcMultiServiceName("news")
@JsonRpcService("/api/news")
public interface NewsJsonRpcService {
//    @JsonRpcMethod("getNewsList")
    List<String> getNewsList();
}
