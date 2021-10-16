package com.melo.springboot.gameservices.service;


import com.melo.springboot.gameservices.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlayerService {

    private static List<Player> players = new ArrayList<>();

    static {
        //Initialize Data
        //Player 1
        Comments firstCommentForPlayer_1 = new Comments("Melo",
                "Cracking game far too much cinematic", "2011-01-03", "6");
        Comments secondCommentForPlayer_1 = new Comments("testingPriest",
                "Not enough shooting for me,far too easy", "2011-04-02", "5");
        Game game1 = new Game("1", "Uncharted 4", "For the first time ever in Uncharted history, " +
                "drive vehicles during gameplay", "Sony", Arrays.asList("PS4"), "16", "99",
                Arrays.asList(firstCommentForPlayer_1, secondCommentForPlayer_1));
        Player player1 = new Player("1", "Melo Mashabane",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(game1)));


        //Player 2
        Comments firstCommentForPlayer_2 = new Comments("Monde",
                "Cracking game!", "2011-01-03", "6");
        Game game2 = new Game("2","Call of Duty, Infinite Warfare","Bloody good game","EA Sports",
                Arrays.asList("PS4","Game boy"),"16","100", Arrays.asList(firstCommentForPlayer_2));
        Player player2 = new Player("2","Monde Mashabane", "Douche bag", new ArrayList<>(Arrays.asList(game2)));


        //Player 3
        Comments firstCommentForPlayer_3 = new Comments("Tei",
                "Cracking game far too much cinematic", "2011-01-03", "6");
        Comments secondCommentForPlayer_3 = new Comments("testingPriest",
                "Not enough shooting for me,far too easy", "2011-04-02", "5");
        Comments thirdCommentForPlayer_3 = new Comments("testingPriest",
                "Not enough shooting for me,far too easy", "2011-04-02", "5");
        Game game3 = new Game("3", "Uncharted 4", "For the first time ever in Uncharted history, " +
                "drive vehicles during gameplay", "Sony", Arrays.asList("PS4"), "16", "95",
                Arrays.asList(firstCommentForPlayer_3, secondCommentForPlayer_3, thirdCommentForPlayer_3));
        Player player3 = new Player("3", "Tei Mashabane",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(game3)));


        //Player 4
        Comments firstCommentForPlayer_4 = new Comments("K",
                "Cracking game!", "2011-01-03", "6");
        Game game4 = new Game("4","Call of Duty, Infinite Warfare","Bloody good game","EA Sports",
                Arrays.asList("PS4","Game boy"),"16","89", Arrays.asList(firstCommentForPlayer_4));
        Player player4 = new Player("4","Monde Mashabane", "Douche bag", new ArrayList<>(Arrays.asList(game4)));

        //Player 4
        Comments firstCommentForPlayer_5 = new Comments("A",
                "Cracking game!", "2011-01-03", "6");
        Game game5 = new Game("5","Call of Duty, Infinite Warfare","Bloody good game","EA Sports",
                Arrays.asList("PS4","Game boy"),"16","90", Arrays.asList(firstCommentForPlayer_5));
        Player player5 = new Player("5","Monde Mashabane", "Douche bag", new ArrayList<>(Arrays.asList(game5)));



        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
    }

    public Game retrieveGame(String gameId) {
        Player player = retrievePlayer(gameId);

        if (player == null) {
            return null;
        }

        for (Game game : player.getGames()) {
            if (game.getId().equals(gameId)) {
                return game;
            }
        }

        return null;
    }

    public GameReport retrieveGameSummary() {
        // The user who has added the most comments across all games
        Map<Player, List<Comments>> userComments = getUserComments();
        Map.Entry<Player, List<Comments>> userWithMostComments = getUserWithMostComments(userComments);

        // The game with the highest sum of likes
        Map<Game, String> gameLikes = getGameLikes();
        Map.Entry<Game, String> gameWithHighestSumOfLikes = getGameWithHighestSumOfLikes(gameLikes);

        // The average likes per game (rounded up to the nearest integer)
        Map<String, String> averageLikesPerGame = getAverageLikesperGame(gameLikes);

        // Create list of reports
        List<Report> reportList = createReports(averageLikesPerGame);

        return new GameReport(userWithMostComments.getKey().getName(),gameWithHighestSumOfLikes.getKey().getTitle(), reportList);
    }

    private List<Report> createReports(Map<String, String> averageLikesPerGame) {

        List<Report> reportsList = new ArrayList();
        for(Map.Entry<String, String> likePerGame: averageLikesPerGame.entrySet()){
            reportsList.add(new Report(likePerGame.getKey(),likePerGame.getValue()));
        }

        return reportsList;
    }

    private Map.Entry<Game, String> getGameWithHighestSumOfLikes(Map<Game, String> gameLikes) {
        Map.Entry<Game, String> maxEntry = null;
        for(Map.Entry<Game, String> entry : gameLikes.entrySet()){

            if(maxEntry == null || Integer.parseInt(entry.getValue()) > Integer.parseInt(maxEntry.getValue())){
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    private Map.Entry<Player, List<Comments>> getUserWithMostComments(Map<Player, List<Comments>> userComments) {
        Map.Entry<Player, List<Comments>> maxEntry = null;
        for(Map.Entry<Player, List<Comments>> entry : userComments.entrySet()){

            if(maxEntry == null || entry.getValue().size() > maxEntry.getValue().size()){
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    private Map<String, String> getAverageLikesperGame(Map<Game, String> gameLikes) {
        int averageLikes = 0;
        Map<String, List<String>> game_vs_aveLike = new HashMap<>();

        ArrayList<String> list;
        for(Map.Entry<Game, String> entry : gameLikes.entrySet()){

            if(game_vs_aveLike.containsKey(entry.getKey().getTitle())){
                list = (ArrayList<String>) game_vs_aveLike.get(entry.getKey().getTitle());
                list.add(entry.getValue());
            } else {
                list = new ArrayList<>();
                list.add(entry.getValue());
                game_vs_aveLike.put(entry.getKey().getTitle(), list);
            }
        }

        // Work out average for each game.
        Map<String, String> game_vs_averLike_Map = getAverage(game_vs_aveLike);




        return game_vs_averLike_Map;

    }

    private Map<String, String> getAverage(Map<String, List<String>> numberOfLikes) {
        Map<String, String> game_vs_averLike_Map = new HashMap<>();
        int divideBy = 0;
        int sum = 0;

        if(!numberOfLikes.isEmpty()){
            for (Map.Entry<String, List<String>> like : numberOfLikes.entrySet()) {

                for(String l : like.getValue()){
                    divideBy++;
                    sum += Integer.parseInt(l);
                }

                int ave = sum / divideBy;

                game_vs_averLike_Map.put(like.getKey(), String.valueOf(ave));

                divideBy = 0;
                sum = 0;
            }
        }



        return game_vs_averLike_Map;
    }

    private Map<Game, String> getGameLikes() {
        Map<Game, String> game_vs_likes = new HashMap<>();
        String likes;

        for(Player p : players) {
            List<Game> games = p.getGames();
            for(Game g : games) {
                likes = g.getLikes();

                game_vs_likes.put(g,likes);
            }
        }

        return game_vs_likes;

    }

    private Map<Player, List<Comments>> getUserComments() {
        Map<Player, List<Comments>> user_vs_comments = new HashMap<>();
        List<Comments> comments = new ArrayList<>();
        for (Player p : players) {

            List<Game> games = p.getGames();
            for (Game g : games) {
                comments = g.getComments();
            }

            user_vs_comments.put(p, comments);
        }

        return user_vs_comments;
    }

    public Player retrievePlayer(String studentId) {
        for (Player student : players) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

}