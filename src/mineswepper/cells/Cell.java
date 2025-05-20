package mineswepper.cells;

public abstract class Cell {
    private boolean cleared;
    private boolean flagged;
    
    public Cell() {
        this.cleared = false;
    }
    
    public void clear() {
        this.cleared = true;
    }
    
    public void toggleFlag() {
        flagged = !flagged;
    }
    
    public boolean isCleared() {
        return this.cleared;
    }
    
    public boolean isFlagged() {
        return this.flagged;
    }
} // end abstract class Cell
