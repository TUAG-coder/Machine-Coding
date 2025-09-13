package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;
    private int size;

    // We shouldn't be able to create object of Board class without the size
    public Board(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        // creates the board
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Cell cell = new Cell(i, j);
                row.add(cell);
            }
            this.cells.add(row);
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board clone() {
        Board board = new Board(this.size);

        for (int i = 0; i < this.size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < this.size; j++) {
                row.add(this.cells.get(i).get(j).clone());
            }
            board.cells.add(row);
        }
        return board;
    }
}
