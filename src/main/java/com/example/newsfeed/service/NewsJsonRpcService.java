package com.example.newsfeed.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService("/api/news")
public interface NewsJsonRpcService {

    @JsonRpcMethod("getNewsList")
    List<String> getNewsList();
}
