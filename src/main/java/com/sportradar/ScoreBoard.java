package com.sportradar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class ScoreBoard {

    private final Map<String, Game> games = new LinkedHashMap<>();

    public void startGame(String homeTeam, String awayTeam) {
        games.put(homeTeam + awayTeam, new Game(homeTeam, awayTeam, 0, 0));
    }

    public List<Game> getGames() {
        return new ArrayList<>(games.values());
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.remove(homeTeam + awayTeam);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Game game = games.get(homeTeam + awayTeam);
        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
    }
}