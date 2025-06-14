package mineswepper.game;

import java.util.Random;
import mineswepper.cells.Cell;
import mineswepper.cells.EmptyCell;
import mineswepper.cells.Mine;
import mineswepper.cells.NumberCell;

public class Game {
    public static final int NANOSECONDS_TO_SECONDS = 1000000000;
    
    private final int startTime;
    private int minesLeft;
    private final Cell[][] field;
    
    public Game(Difficulty difficulty) {
        startTime = (int) (System.nanoTime() / NANOSECONDS_TO_SECONDS);
        // create field with correct size based on difficulty
        field = new Cell[difficulty.getWidth()][difficulty.getHeight()];
        minesLeft = difficulty.numMines();
        Random random = new Random();
        // randomly place mines around the field
        for (int i = 0; i < difficulty.numMines(); i++) {
            boolean minePlaced = false;
            while (!minePlaced) {
                int x = random.nextInt(field.length);
                int y = random.nextInt(field[0].length);
                if (field[x][y] == null) {
                    field[x][y] = new Mine();
                    // break while loop
                    minePlaced = true;
                } // if there's already a mine here, re-run RNG until there isn't
            } // end while
        } // end for
        // loop through the field and assign the remaining cells accordingly
        int surroundingMines;
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                // if there is not a mine there (we still need to assign this cell)
                if (field[x][y] == null) {
                    // check each of the surrounding cells and see if it is a mine
                    surroundingMines = 0;
                    Cell topLeft;
                    try {
                        topLeft = field[x-1][y+1];
                        if (topLeft instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        topLeft = null;
                    }
                    Cell topCenter;
                    try {
                        topCenter = field[x][y+1];
                        if (topCenter instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        topCenter = null;
                    }
                    Cell topRight;
                    try {
                        topRight = field[x+1][y+1];
                        if (topRight instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        topRight = null;
                    }
                    Cell centerLeft;
                    try {
                        centerLeft = field[x-1][y];
                        if (centerLeft instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        centerLeft = null;
                    }
                    Cell centerRight;
                    try {
                        centerRight = field[x+1][y];
                        if (centerRight instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        centerRight = null;
                    }
                    Cell bottomLeft;
                    try {
                        bottomLeft = field[x-1][y-1];
                        if (bottomLeft instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        bottomLeft = null;
                    }
                    Cell bottomCenter;
                    try {
                        bottomCenter = field[x][y-1];
                        if (bottomCenter instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        bottomCenter = null;
                    }
                    Cell bottomRight;
                    try {
                        bottomRight = field[x+1][y-1];
                        if (bottomRight instanceof Mine) {
                            surroundingMines++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        bottomRight = null;
                    }
                    // assign the value of this cell based on how many mines are around it
                    if (surroundingMines > 0) {
                        field[x][y] = new NumberCell(surroundingMines);
                    } else {
                        field[x][y] = new EmptyCell(
                                topLeft,
                                topCenter,
                                topRight,
                                centerLeft,
                                centerRight,
                                bottomLeft,
                                bottomCenter,
                                bottomRight);
                    }
                } // end if (field[x][y] == null)
            } // end inner for
        } // end outer for
    } // end Game()
    
    public synchronized void clear(int x, int y) {
        field[x][y].clear();
    }
    
    public synchronized void toggleFlag(int x, int y) {
        field[x][y].toggleFlag();
        if (field[x][y].isFlagged()) { // the cell just got flagged; decrement mine count
            minesLeft--;
        } else { // the cell was unflagged
            minesLeft++;
        }
    }
    
    public synchronized boolean isCleared(int x, int y) {
        return field[x][y].isCleared();
    }
    
    public synchronized boolean isFlagged(int x, int y) {
        return field[x][y].isFlagged();
    }
    
    public synchronized int getMinesLeft() {
        return minesLeft;
    }
    
    public synchronized Cell getCell(int x, int y) {
        return field[x][y];
    }
    
    public synchronized int getTime() {
        return (int) (System.nanoTime() / NANOSECONDS_TO_SECONDS) - startTime;
    }
} // end class Game