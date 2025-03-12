package com.example.newsfeed.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@Table("feeds")
@NoArgsConstructor
public class Feed {
    @Id
    private Long id;

    private String name;

    private String url;

    private boolean enabled;

    private Timestamp lastFetched;
}
