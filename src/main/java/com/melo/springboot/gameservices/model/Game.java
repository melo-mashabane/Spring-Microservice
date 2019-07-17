package com.melo.springboot.gameservices.model;


import java.util.List;

public class Game {
    private String id;
    private String title;
    private String description;
    private String by;
    private List<String> platform;
    private String age_rating;
    private String likes;
    private List<Comments> comments;


    public Game(String id, String title, String description, String by, List<String> platform, String age_rating, String likes, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.by = by;
        this.platform = platform;
        this.age_rating = age_rating;
        this.likes = likes;
        this.comments = comments;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public String getAge_rating() {
        return age_rating;
    }

    public void setAge_rating(String age_rating) {
        this.age_rating = age_rating;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return String.format(
                "title=%s, description=%s]",title,
                description);
    }
}