package com.example.newsfeed.repository;

import com.example.newsfeed.entity.NewsEntity;
import com.example.newsfeed.entity.NewsWithFeedNameEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository  extends CrudRepository<NewsEntity, Long> {
    List<NewsEntity> findByGuidIn(List<String> guids);
    List<NewsEntity> findByFeedId(Long feedId);

    @Query("SELECT n.*, f.name AS feed_name FROM news n JOIN feeds f ON n.feed_id = f.id WHERE n.feed_id = :feedId ORDER BY n.pub_date DESC LIMIT 100")
    List<NewsWithFeedNameEntity> findNewsWithFeedNameByFeedId(Long feedId);

    @Query("SELECT n.*, f.name AS feed_name FROM news n JOIN feeds f ON n.feed_id = f.id ORDER BY n.pub_date DESC LIMIT 100")
    List<NewsWithFeedNameEntity> findTopNewsWithFeedName();

}
