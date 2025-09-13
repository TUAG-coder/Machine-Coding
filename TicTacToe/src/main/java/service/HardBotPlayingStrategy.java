package service;

import enums.CellState;
import models.*;

import java.util.List;

public class HardBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Player player, Game game) {
        Board board = game.getBoard();
        for (List<Cell> row: board.getCells()) {
            for (Cell cell: row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    cell.setPlayer(player);
                    cell.setCellState(CellState.FILLED);
                    Move move = new Move(cell, player);
                    List<Move> moves = game.getMoves();
                    moves.add(move);
                    List<Board> playedBoards = game.getPlayedBoards();
                    playedBoards.add(game.getBoard().clone());
                    return move;
                }
            }
        }
        return null;
    }
}
