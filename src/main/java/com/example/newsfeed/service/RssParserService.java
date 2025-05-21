package com.example.newsfeed.service;

import com.example.newsfeed.entity.FeedEntity;
import com.example.newsfeed.entity.NewsEntity;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.io.InputStreamReader;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssParserService {

    public List<NewsEntity> parse(FeedEntity newsFeed) throws Exception {
        URL feedUrl = new URL(newsFeed.getUrl());
        SyndFeedInput input = new SyndFeedInput();
        try (InputStreamReader reader = new InputStreamReader(feedUrl.openStream())) {
            SyndFeed feed = input.build(reader);

            List<NewsEntity> newsItems = new ArrayList<>();

            for (SyndEntry entry : feed.getEntries()) {
                NewsEntity item = new NewsEntity();
                item.setFeedId(newsFeed.getId());
                item.setGuid(entry.getLink());
                item.setAuthor(entry.getAuthor());
                item.setTitle(entry.getTitle());
                item.setLink(entry.getLink());

                String rawDescription = entry.getDescription() != null && entry.getDescription().getValue() != null
                        ? entry.getDescription().getValue()
                        : "";
                String cleanDescription = Jsoup.clean(rawDescription, Safelist.basicWithImages());
                item.setDescription(cleanDescription);

                item.setPubDate(
                        entry.getPublishedDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                );
                newsItems.add(item);
            }

            return newsItems;


        } catch (Exception e) {
            System.err.println("Ошибка при парсинге RSS: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

