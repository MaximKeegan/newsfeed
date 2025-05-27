package com.example.newsfeed.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(String message) {
        super(message);
    }
}