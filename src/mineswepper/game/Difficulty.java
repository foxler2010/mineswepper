package mineswepper.game;

public enum Difficulty {
    EASY(10, 8, 8),
    INTERMEDIATE(40, 16, 16),
    EXPERT(99, 30, 16);
    
    private final int mines;
    private final int width;
    private final int height;
    
    Difficulty(int mines, int width, int height) {
        this.mines = mines;
        this.width = width;
        this.height = height;
    }
    
    public synchronized int numMines() {
        return this.mines;
    }
    
    public synchronized int getWidth() {
        return this.width;
    }
    
    public synchronized int getHeight() {
        return this.height;
    }
} // end enum Difficulty
