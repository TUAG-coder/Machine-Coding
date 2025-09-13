package controller;

import enums.BotDifficultyLevel;
import enums.PlayerType;
import exception.InvalidCellChosenException;
import models.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private PlayerService playerService;
    private GameService gameService;
    private Scanner sc;
    private BotPlayingStrategy botPlayingStrategy;

    public GameController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
        sc = new Scanner(System.in);
    }

    public List<Player> generatePlayerList(int size) {
        System.out.println("Please enter 1 if you want to play against a Bot, else else 0");
        int opponent = sc.nextInt();
        sc.nextLine();

        List<Player> players = new ArrayList<>();
        Bot bot;

        if (opponent == 1) {
            System.out.println("Enter difficulty level: ");
            String difficultyLevel = sc.nextLine();
            BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.valueOf(difficultyLevel.toUpperCase());
            if (botDifficultyLevel.equals(BotDifficultyLevel.EASY)) {
                this.botPlayingStrategy = new EasyBotPlayingStrategy();
            } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
                this.botPlayingStrategy = new MediumBotPlayingStrategy();
            } else if (botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
                this.botPlayingStrategy = new HardBotPlayingStrategy();
            }
            bot = playerService.createBot("BOT", '$', botDifficultyLevel);
            size--; // As BOT took the spot as one of the players
            players.add(bot);
        }
        for (int i = 0; i < size-1; i++) {
            System.out.println("Enter the name of the player: " + (i+1));
            String name = sc.nextLine();
            System.out.println("Enter the character for the player: " + name);
            char symbol = sc.nextLine().charAt(0);
            players.add(playerService.createPlayer(name, symbol));
        }
        return players;
    }

    public Move createMove(Player player, Game game) throws InvalidCellChosenException{
        if (player.getPlayerType().equals(PlayerType.BOT)) {
//            BotPlayingStrategy strategy = new RandomBotPlayingStrategy();

            // Strategy design pattern, we're assigning the object in generatePlayerList() method
            BotPlayingStrategy strategy = this.botPlayingStrategy;
            Move move = strategy.makeMove(player, game);
            return move;
        } else {
            System.out.println("Please enter the row number");
            int row = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter the column number");
            int column = sc.nextInt();
            try {
                Move move = gameService.executeMove(game, player, row, column);
                return move;
            } catch (InvalidCellChosenException ex) {
                throw new InvalidCellChosenException(ex.getMessage());
            }
        }
    }

    public Player checkWinner(Board board, Move currentMove, CheckWinnerUtil checkWinnerUtil) {
        return checkWinnerUtil.checkWinner(board, currentMove);
    }
}
