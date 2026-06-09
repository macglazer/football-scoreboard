package com.sportradar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}