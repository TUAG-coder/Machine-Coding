import controller.GameController;
import enums.GameState;
import exception.GameDrawnException;
import exception.InvalidCellChosenException;
import models.Game;
import models.Move;
import models.Player;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        BoardService boardService = new BoardService();


        System.out.println("WELCOME TO TIC-TAC-TOE GAME");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the size of the game");
        int size = sc.nextInt();
        GameController gameController = new GameController(playerService, gameService);
        List<Player> players = gameController.generatePlayerList(size);
        Game game = gameService.createGame(size, players);
        game = gameService.startGame(game);

        while(true) {
            int nextPlayerIndex = game.getNextMovePlayerIndex();
            Player currentPlayer = game.getPlayers().get(nextPlayerIndex);
            System.out.println("Player to make move: " + currentPlayer.getName());
            // printing the board
            boardService.printBoard(game.getBoard());
            try {
                Move move = gameController.createMove(currentPlayer, game);
                Player winner = gameController.checkWinner(game.getBoard(), move, game.getCheckWinnerUtil());
                CheckWinnerUtil checkWinnerUtil1 = game.getCheckWinnerUtil();
//                System.out.println("Row Map: " + checkWinnerUtil1.getRowMaps());
//                System.out.println("Column Map: " + checkWinnerUtil1.getColumnMaps());
//                System.out.println("Corner Map: " + checkWinnerUtil1.getCornerMap());
//                System.out.println("Diagonal Map: " + checkWinnerUtil1.getDiagonalMap());
//                System.out.println("Anti-diagonal Map: " + checkWinnerUtil1.getAntiDiagonalMap());
                if (winner != null) {
                    game.setGameState(GameState.WINNER_DONE);
                    System.out.println("Winner is: " + winner.getName());
                    boardService.printBoard(game.getBoard());
                    break;
                }
            } catch (InvalidCellChosenException ex) {
                System.out.println("WRONG cell chosen !!!");
                break;
            } catch (GameDrawnException ex) {
                System.out.println("Game is drawn, please start again");
                break;
            }
            game.setNextMovePlayerIndex((game.getNextMovePlayerIndex() + 1) % players.size());
        }
    }
}
