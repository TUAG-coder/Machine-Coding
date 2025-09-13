package service;

import exception.GameDrawnException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckWinnerUtil {
    private List<HashMap<Character, Integer>> rowMaps;
    private List<HashMap<Character, Integer>> columnMaps;
    private HashMap<Character, Integer> diagonalMap;
    private HashMap<Character, Integer> antiDiagonalMap;
    private HashMap<Character, Integer> cornerMap;
    private int size;

    public List<HashMap<Character, Integer>> getRowMaps() {
        return rowMaps;
    }

    public void setRowMaps(List<HashMap<Character, Integer>> rowMaps) {
        this.rowMaps = rowMaps;
    }

    public List<HashMap<Character, Integer>> getColumnMaps() {
        return columnMaps;
    }

    public void setColumnMaps(List<HashMap<Character, Integer>> columnMaps) {
        this.columnMaps = columnMaps;
    }

    public HashMap<Character, Integer> getDiagonalMap() {
        return diagonalMap;
    }

    public void setDiagonalMap(HashMap<Character, Integer> diagonalMap) {
        this.diagonalMap = diagonalMap;
    }

    public HashMap<Character, Integer> getAntiDiagonalMap() {
        return antiDiagonalMap;
    }

    public void setAntiDiagonalMap(HashMap<Character, Integer> antiDiagonalMap) {
        this.antiDiagonalMap = antiDiagonalMap;
    }

    public HashMap<Character, Integer> getCornerMap() {
        return cornerMap;
    }

    public void setCornerMap(HashMap<Character, Integer> cornerMap) {
        this.cornerMap = cornerMap;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CheckWinnerUtil(int size) {
        this.size = size;
        rowMaps = new ArrayList<>();
        columnMaps = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            rowMaps.add(hashMap);

            // DON'T MAKE THIS MISTAKE, keep separate loop for columnMaps,
            // otherwise same Hashmap object will be added to both
            // when you'll make changes in rowMaps.get(0) hashmap, it will be reflected in columnMaps.get(0)
            // as well, because both with point to same reference
            // columnMaps.add(hashMap);
        }
        for (int i = 0; i < size; i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            columnMaps.add(hashMap);
        }
        diagonalMap = new HashMap<>();
        antiDiagonalMap = new HashMap<>();
        cornerMap = new HashMap<>();
    }

    public Player checkWinner(Board board, Move currentMove) {
        int row = currentMove.getCell().getRow(), column = currentMove.getCell().getColumn();
        char symbol = currentMove.getPlayer().getSymbol();
        HashMap<Character, Integer> rowMap = this.rowMaps.get(row);
        HashMap<Character, Integer> columnMap = this.columnMaps.get(column);

        //Updating hashmaps
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1);

        columnMap.put(symbol, columnMap.getOrDefault(symbol, 0) + 1);

        if (row == column) {
            diagonalMap.put(symbol, diagonalMap.getOrDefault(symbol, 0) + 1);
        }

        if ((row + column) == this.size-1) {
            antiDiagonalMap.put(symbol, antiDiagonalMap.getOrDefault(symbol, 0) + 1);
        }

        if ((row == 0 || row == this.size-1) && (column == 0 || column == this.size-1)) {
            cornerMap.put(symbol, cornerMap.getOrDefault(symbol, 0) + 1);
        }

        // Checking the size of the hashmaps
        // Changed get() to getOrDefault() later because when a players enter any symbol,
        // it may not necessarily be entered in a corner cell or any of the diagonals,
        // so symbol will be "null" for them in that case
        if (rowMap.get(symbol) == this.size
            || columnMap.get(symbol) == this.size
            || diagonalMap.getOrDefault(symbol, 0) == this.size
            || antiDiagonalMap.getOrDefault(symbol, 0) == this.size
            || cornerMap.getOrDefault(symbol, 0) == 4
        ) {
            return currentMove.getPlayer();
        }

        // TODO: maintain count for invalid hashmaps and if the count becomes = size, throw exception
        if (checkDraw()) {
            throw new GameDrawnException("No more winner possible");
        }
        return null;
    }

    private boolean checkDraw() {
        for (HashMap<Character, Integer> rowMap: this.rowMaps) {
            // Even if there is a single row which contains only a single element or no element, game can be won
            if (rowMap.size() <= 1) {
                return false;
            }
        }
        for (HashMap<Character, Integer> columnMap: this.columnMaps) {
            // Even if there is a single column which contains only a single element or no element, game can be won
            if (columnMap.size() <= 1) {
                return false;
            }
        }
        if (this.cornerMap.size() <= 1) {
            return false;
        }
        if(this.diagonalMap.size() <= 1) {
            return false;
        }
        if (this.antiDiagonalMap.size() <= 1) {
            return false;
        }
        return true;
    }
}
