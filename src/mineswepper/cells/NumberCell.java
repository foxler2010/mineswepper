package mineswepper.cells;

public class NumberCell extends Cell {

    private final int numMines;
    
    private Cell topLeft;
    private Cell topCenter;
    private Cell topRight;
    private Cell centerLeft;
    private Cell centerRight;
    private Cell bottomLeft;
    private Cell bottomCenter;
    private Cell bottomRight;
    
    public NumberCell(int numMines) {
        if (numMines > 0 && numMines <= 8) {
            this.numMines = numMines;
        } else throw new IllegalArgumentException("numMines must be between 1 and 8");
    }
    
    public int getNumMines() {
        return numMines;
    }
    
    public void chord() {
        // TODO
    }
} // end class NumberCell
