package com.axelspringer.businessintegration.blog.dto;

import com.axelspringer.businessintegration.blog.domain.BlogPost;
import com.axelspringer.businessintegration.blog.domain.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BlogPostOverview {

    private Long id;

    private String title;

    private String contentPreview;

    private String content;

    private String timeSinceLastEdit;

    private Long authorId;

    private String authorUsername;

    private long views;

    private final int maxContentPreviewLength = 150;

    public BlogPostOverview(BlogPost blogPost) {
        this.id = blogPost.getId();
        this.title = blogPost.getTitle();
        this.content = blogPost.getContent();
        this.views = blogPost.getViews();

        if (content.length() > maxContentPreviewLength) {
            int lastSpace = content.substring(0, maxContentPreviewLength).lastIndexOf(" ");
            this.contentPreview = content.substring(0, lastSpace) + "...";
        } else {
            this.contentPreview = content;
        }

        long dayDifference = ChronoUnit.DAYS.between(blogPost.getEditedAt(), LocalDateTime.now());

        String verbalDayDifference;
        if (dayDifference == 0) {
            verbalDayDifference = "today";
        } else if (dayDifference == 1) {
            verbalDayDifference = "yesterday";
        } else {
            verbalDayDifference = dayDifference + " days ago";
        }

        this.timeSinceLastEdit = verbalDayDifference;

        if (blogPost.getAuthor() != null) {
            this.authorId = blogPost.getAuthor().getId();
            this.authorUsername = blogPost.getAuthor().getUsername();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getContentPreview() {
        return contentPreview;
    }

    public String getTimeSinceLastEdit() {
        return timeSinceLastEdit;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public long getViews() {
        return views;
    }
}
