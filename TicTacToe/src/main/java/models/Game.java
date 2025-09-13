package models;

import enums.GameState;
import exception.InvalidBoardSizeException;
import exception.InvalidNumberOfPlayersException;
import service.CheckWinnerUtil;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private GameState gameState;
    private List<Player> players;
    private Player winner;
    private int nextMovePlayerIndex;
    private List<Move> moves;

    // For Doraemon UNDO approach
    private List<Board> playedBoards;

    /*
    This one is added later. As we wanted to get checkWinner of CheckWinnerUtil class from checkWinner()
    method of GameController, we needed its object. So we're passing it as a parameter from the Main class.
    Therefore, we need Game class to have CheckWinnerUtil attribute
     */
    private CheckWinnerUtil checkWinnerUtil;

    /*
    To start a Game (create a Game object) I only need to have board and players,
    so we're going to define only that in constructor.
    And for Board, we need size, therefore, we need to mention only required
    attributes in our Builder class.
     */
    private Game(Board board, List<Player> players, CheckWinnerUtil checkWinnerUtil) {
        this.board = board;
        this.players = players;
        this.gameState = GameState.YET_TO_START;
        this.nextMovePlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.playedBoards = new ArrayList<>();
        this.checkWinnerUtil = checkWinnerUtil;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getPlayedBoards() {
        return playedBoards;
    }

    public void setPlayedBoards(List<Board> playedBoards) {
        this.playedBoards = playedBoards;
    }

    public CheckWinnerUtil getCheckWinnerUtil() {
        return checkWinnerUtil;
    }

    public void setCheckWinnerUtil(CheckWinnerUtil checkWinnerUtil) {
        this.checkWinnerUtil = checkWinnerUtil;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int size;
        private List<Player> players;
        private CheckWinnerUtil checkWinnerUtil;

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder checkWinnerUtil(CheckWinnerUtil checkWinnerUtil) {
            this.checkWinnerUtil = checkWinnerUtil;
            return this;
        }

        public void validate() {
            if (size < 3 || size > 10) {
                throw new InvalidBoardSizeException("Board size should be between 3 and 10");
            }
            if (players.size() != (size-1)) {
                throw new InvalidNumberOfPlayersException("Players should be board size-1");
            }
        }

        public Game build() {
            validate();
            Board gameBoard = new Board(size);
            return new Game(gameBoard, players, checkWinnerUtil);
        }
    }
}
