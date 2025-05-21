package com.example.newsfeed.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class NewsWithFeedNameEntity extends NewsEntity {
    private String feedName;
}