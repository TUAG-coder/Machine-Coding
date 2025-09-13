package service;

import enums.BotDifficultyLevel;
import enums.PlayerType;
import exception.DuplicateSymbolException;
import models.Bot;
import models.Player;

import java.util.HashSet;

public class PlayerService {
    // To handle ID for the players
    private static int counter = 1;
    private HashSet<Character> symbolSet;

    public PlayerService() {
        this.symbolSet = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol) {
        if (symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("Duplicate symbols are not allowed");
        }
        symbolSet.add(symbol);
        return new Player(
                counter++,
                name,
                symbol,
                PlayerType.HUMAN
        );
    }

    public Bot createBot(String name, char symbol, BotDifficultyLevel botDifficultyLevel) {
        if (symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("Duplicate symbols are not allowed");
        }
        symbolSet.add(symbol);
        return new Bot(
                counter++,
                name,
                symbol,
                PlayerType.BOT,
                botDifficultyLevel
        );
    }
}
