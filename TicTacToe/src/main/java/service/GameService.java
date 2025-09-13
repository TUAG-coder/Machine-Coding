package service;

import enums.CellState;
import enums.GameState;
import exception.InvalidCellChosenException;
import models.*;

import java.util.Collections;
import java.util.List;

public class GameService {
    // We shouldn't define "private Game game" as an attribute in this class.
    // Because this class should hold only the logic, not the objects
    // What if this class is being used by multiple games, and if I write code in such a way
    // that this class contains Game object, then one service object will never be usable for any other game, i.e,
    // we'll have to create new service object for new Game, which is not a right idea
    // Therefore, we're passing Game object as an parameter in startGame method
    // Service class should contain only logic, and it should be Singleton as one object needs to be there for it
    public Game createGame(int size, List<Player> players) {
        CheckWinnerUtil checkWinnerUtil = new CheckWinnerUtil(size);
        Game newGame = Game.builder()
                            .size(size)
                            .players(players)
                            .checkWinnerUtil(checkWinnerUtil)
                            .build();
        return newGame;
    }

    public Game startGame(Game game) {
        game.setGameState(GameState.IN_PROGRESS);
        List<Player> players = game.getPlayers();
        Collections.shuffle(players);
        return game;
    }

    public Move executeMove(Game game, Player player, int row, int column) {
        Cell cell = game.getBoard().getCells().get(row).get(column);
        if (cell.getCellState() != CellState.EMPTY) {
            throw new InvalidCellChosenException("Cell is already");
        }
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);

        Move move = new Move(cell, player);

        /*
         We need to keep track of already played moves and played boards, and we need to add the new move
         and new board status to those lists
         */
        List<Move> moves = game.getMoves();
        moves.add(move);

        /*
        IMPORTANT
        This will not work as we're having same reference of Game

        List<Board> playedBoards = game.getPlayedBoards();
        playedBoards.add(game.getBoard());
         */

        // Therefore, we need to create deep copy of the Board object
        List<Board> playedBoards = game.getPlayedBoards();
        playedBoards.add(game.getBoard().clone());

        return move;
    }
}
