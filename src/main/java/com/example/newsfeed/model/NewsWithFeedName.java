package com.example.newsfeed.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class NewsWithFeedName extends News {
    private String feedName;
}