package com.example.newsfeed.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@Table("feeds")
@NoArgsConstructor
public class Feed {
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
