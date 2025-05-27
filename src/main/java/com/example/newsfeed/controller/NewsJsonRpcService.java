package com.example.newsfeed.controller;

import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import com.googlecode.jsonrpc4j.*;

import java.util.List;

@JsonRpcService("/api/news")
public interface NewsJsonRpcService {

    @JsonRpcMethod("getNewsList")
    List<NewsWithFeedNameEntity> getNewsList();

    @JsonRpcMethod("getNewsList")
    @JsonRpcErrors({
            @JsonRpcError(exception = RuntimeException.class, code = -32001, message = "News not found for feedId")
    })
    List<NewsWithFeedNameEntity> getNewsList(@JsonRpcParam("feedId") Long feedId);
}
