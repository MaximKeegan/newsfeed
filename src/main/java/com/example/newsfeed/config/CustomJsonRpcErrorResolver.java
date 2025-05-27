package com.example.newsfeed.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;

import java.lang.reflect.Method;
import java.util.List;

class CustomJsonRpcErrorResolver implements ErrorResolver {
    @Override
    public JsonError resolveError(Throwable throwable, Method method, List<JsonNode> arguments) {
        String message = throwable.getMessage() != null ? throwable.getMessage() : "Unknown error";
        int code = method.getName().equals("getNewsList") && arguments.isEmpty() ? -32001 : -32002;
        return new JsonError(code, message, message);
    }
}