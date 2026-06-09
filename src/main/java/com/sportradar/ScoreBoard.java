package com.sportradar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final Map<String, Game> games = new LinkedHashMap<>();

    public void startGame(String homeTeam, String awayTeam) {
        boolean teamAlreadyPlaying = games.values().stream()
                .anyMatch(g -> g.getHomeTeam().equals(homeTeam)
                        || g.getAwayTeam().equals(homeTeam)
                        || g.getHomeTeam().equals(awayTeam)
                        || g.getAwayTeam().equals(awayTeam));
        if (teamAlreadyPlaying) {
            throw new IllegalArgumentException("Team is already playing");
        }
        games.put(homeTeam + awayTeam, new Game(homeTeam, awayTeam, 0, 0));
    }

    public List<Game> getGames() {
        return new ArrayList<>(games.values());
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.remove(homeTeam + awayTeam);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }

        Game game = games.get(homeTeam + awayTeam);
        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
    }

    public List<Game> getSummary() {
        return games.values().stream()
                .sorted(Comparator.comparingInt((Game g) -> g.getHomeScore() + g.getAwayScore())
                        .reversed()
                        .thenComparing(Comparator.comparingLong(Game::getStartTime).reversed()))
                .collect(Collectors.toList());
    }
}