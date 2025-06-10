package com.example.newsfeed;

import com.example.newsfeed.IntegrationTestBase;
import com.example.newsfeed.component.RssUpdateScheduler;
import com.example.newsfeed.entity.FeedEntity;
import com.example.newsfeed.entity.NewsEntity;
import com.example.newsfeed.repository.FeedRepository;
import com.example.newsfeed.repository.NewsRepository;
import com.example.newsfeed.service.RssParserService;
import com.example.newsfeed.service.NewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;

class NewsServiceTest extends IntegrationTestBase {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Mock
//    @Autowired
    private RssParserService rssParserService;

    @InjectMocks
    private NewsService newsService;

    @Test
    @Sql(statements = "INSERT INTO feeds (id, name, url, enabled) VALUES (1, 'Test Feed', 'http://example.com/rss', true)")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = "DELETE FROM news; DELETE FROM feeds;")
    void testUpdateNews() throws Exception {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setGuid("test-guid");
        newsEntity.setTitle("Test News");
        newsEntity.setLink("http://example.com/news");
        newsEntity.setDescription("Test description");
        newsEntity.setPubDate(LocalDateTime.now());
        newsEntity.setFeedId(1L);

        when(rssParserService.parse(any(FeedEntity.class))).thenReturn(List.of(newsEntity));

        newsService.updateNews();

        verify(rssParserService, times(1)).parse(any(FeedEntity.class)); // Проверяем, что parse вызван ровно один раз

        List<NewsEntity> savedNews = newsRepository.findByFeedId(1L);
        assertFalse(savedNews.isEmpty(), "No news saved in repository");
        assertEquals("test-guid", savedNews.get(0).getGuid(), "GUID does not match");
        assertEquals("Test News", savedNews.get(0).getTitle(), "Title does not match");

        FeedEntity feed = feedRepository.findTopEnabledOrderByLastFetchedAscendingForUpdate();
        assertEquals(true, feed.isEnabled(), "Feed is not enabled");
        assertEquals("Test Feed", feed.getName(), "Feed does not match name");
    }
}