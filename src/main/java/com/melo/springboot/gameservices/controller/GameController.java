package com.melo.springboot.gameservices.controller;


import com.melo.springboot.gameservices.model.Game;
import com.melo.springboot.gameservices.model.GameReport;
import com.melo.springboot.gameservices.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/games/{gameId}")
    public Game retrieveDataAboutGames(@PathVariable String gameId) {
        return playerService.retrieveGame(gameId);
    }

    @GetMapping("/games/report")
    public GameReport retrieveSummaryAboutGames() {
        return playerService.retrieveGameSummary();
    }
}