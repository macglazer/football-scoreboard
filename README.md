# football-scoreboard
Live Football World Cup Score Board library implementation

## Description
A simple in-memory library that manages a live football World Cup score board.
The library supports starting games, updating scores, finishing games and retrieving a summary sorted by total score.

## How to run tests
```bash
mvn test
```

## Project structure
- `Game` - represents a football match with home/away teams and scores
- `ScoreBoard` - main class managing the score board operations

## Operations
- **startGame(homeTeam, awayTeam)** - starts a new game with score 0-0
- **finishGame(homeTeam, awayTeam)** - removes a game from the score board
- **updateScore(homeTeam, awayTeam, homeScore, awayScore)** - updates the score of a game
- **getSummary()** - returns games ordered by total score (desc), ties broken by most recently added

## Assumptions
- In-memory store only, no persistence
- Map key is a combination of home and away team names
- When two games have the same total score, the more recently started game appears first (resolved using `System.nanoTime()`)
- A team can only participate in one game at a time
- Scores cannot be negative