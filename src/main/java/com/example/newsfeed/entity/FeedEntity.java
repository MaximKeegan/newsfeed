package com.example.newsfeed.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table("feeds")
@NoArgsConstructor
public class FeedEntity {
    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("url")
    private String url;

    @Column("enabled")
    private boolean enabled;

    @Column("last_fetched")
    private Timestamp lastFetched;
}
