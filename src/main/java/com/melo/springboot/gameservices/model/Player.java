package com.melo.springboot.gameservices.model;

import java.util.List;

public class Player {

    private String id;
    private String name;
    private String description;
    private List<Game> games;

    public Player(String id, String name, String description, List<Game> games) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.games = games;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return String.format(
                "Player [id=%s, name=%s, description=%s, games=%s]", id,
                name, description, games);
    }
}

