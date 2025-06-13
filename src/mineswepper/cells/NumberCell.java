package mineswepper.cells;

public class NumberCell extends Cell {
    private final int numMines;
    
    public NumberCell(int numMines) {
        if (numMines > 0 && numMines <= 8) {
            this.numMines = numMines;
        } else throw new IllegalArgumentException("numMines must be between 1 and 8");
    }
    
    public int getNumMines() {
        return numMines;
    }
} // end class NumberCell
