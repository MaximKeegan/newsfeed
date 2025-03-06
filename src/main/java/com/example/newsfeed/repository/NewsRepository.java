package com.example.newsfeed.repository;

import com.example.newsfeed.models.News;
import com.example.newsfeed.models.NewsWithFeedName;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository  extends CrudRepository<News, Long> {
    List<News> findByGuidIn(List<String> guids);
    List<News> findByFeedId(Long feedId);

    @Query("SELECT n.*, f.name AS feed_name FROM news n JOIN feeds f ON n.feed_id = f.id WHERE n.feed_id = :feedId")
    List<NewsWithFeedName> findNewsWithFeedNameByFeedId(Long feedId);

    @Query("SELECT n.*, f.name AS feed_name FROM news n JOIN feeds f ON n.feed_id = f.id ORDER BY n.pub_date DESC LIMIT 100")
    List<NewsWithFeedName> findTopNewsWithFeedName();

}
