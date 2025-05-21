package com.example.newsfeed.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("news")
@NoArgsConstructor
public class NewsEntity {
    @Id
    @Column("id")
    private Long id;                // Уникальный идентификатор в базе

    @Column("guid")
    private String guid;           // Уникальный идентификатор записи в RSS

    @Column("author")
    private String author;         // Автор новости

    @Column("title")
    private String title;          // Заголовок новости

    @Column("link")
    private String link;           // Ссылка на новость

    @Column("description")
    private String description;    // Описание новости (может быть пустым)

    @Column("pub_date")
    private LocalDateTime pubDate;     // Дата публикации

    @Column("enclosure_url")
    private String enclosureUrl;   // URL вложения (из атрибута url в <enclosure>)

    @Column("enclosure_type")
    private String enclosureType;  // Тип вложения (из атрибута type в <enclosure>)

    @Column("enclosure_length")
    private Long enclosureLength;  // Длина вложения (из атрибута length в <enclosure>)

    @Column("category")
    private String category;       // Категория новости

//    @Column("created_at")
//    private Timestamp createdAt;   // Дата создания записи в базе

    @Column("feed_id")
    private Long feedId;
}
