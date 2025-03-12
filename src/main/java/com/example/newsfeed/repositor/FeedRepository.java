package com.example.newsfeed.repositor;

import com.example.newsfeed.model.Feed;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedRepository  extends CrudRepository<Feed, Long> {

    @Query("SELECT * FROM feeds WHERE enabled = true ORDER BY last_fetched ASC LIMIT 1")
    Feed findTopEnabledOrderByLastFetchedAscending();
}
