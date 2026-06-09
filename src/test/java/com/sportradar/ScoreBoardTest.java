package com.sportradar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void shouldAddGameToScoreBoard() {
        scoreBoard.startGame("Mexico", "Canada");
        assertEquals(1, scoreBoard.getGames().size());
    }

    @Test
    void shouldStartGameWithZeroScore() {
        scoreBoard.startGame("Mexico", "Canada");
        Game game = scoreBoard.getGames().get(0);
        assertEquals(0, game.getHomeScore());
        assertEquals(0, game.getAwayScore());
    }

    @Test
    void shouldStartGameWithCorrectTeams() {
        scoreBoard.startGame("Mexico", "Canada");
        Game game = scoreBoard.getGames().get(0);
        assertEquals("Mexico", game.getHomeTeam());
        assertEquals("Canada", game.getAwayTeam());
    }

    @Test
    void shouldRemoveGameFromScoreBoard() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");
        assertEquals(0, scoreBoard.getGames().size());
    }

    @Test
    void shouldRemoveCorrectGame() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.finishGame("Mexico", "Canada");
        assertEquals(1, scoreBoard.getGames().size());
        assertEquals("Spain", scoreBoard.getGames().get(0).getHomeTeam());
    }

    @Test
    void shouldUpdateScore() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        Game game = scoreBoard.getGames().get(0);
        assertEquals(0, game.getHomeScore());
        assertEquals(5, game.getAwayScore());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentGame() {
        assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.updateScore("Mexico", "Canada", 0, 5)
        );
    }

    @Test
    void shouldReturnSummaryOrderedByTotalScore() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<Game> summary = scoreBoard.getSummary();

        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Germany", summary.get(4).getHomeTeam());
    }
}