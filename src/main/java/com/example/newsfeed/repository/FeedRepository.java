package com.example.newsfeed.repository;

import com.example.newsfeed.entity.FeedEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedRepository  extends CrudRepository<FeedEntity, Long> {

    @Query("SELECT * FROM feeds WHERE enabled = true ORDER BY last_fetched ASC LIMIT 1 FOR UPDATE SKIP LOCKED")
    FeedEntity findTopEnabledOrderByLastFetchedAscendingForUpdate();
}
