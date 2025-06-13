package mineswepper.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridHelper {
    private final GraphicsContext gc;
    
    public GridHelper(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
    }
    
    public void drawFullSquare(int cellX, int cellY) {
        double gridX = CoordHelper.cellToGrid(cellX);
        double gridY = CoordHelper.cellToGrid(cellY);
        // main cell body
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(gridX, gridY, CoordHelper.cellSize(), CoordHelper.cellSize());
        // darker edges from bottom-right corner
        gc.setFill(Color.GRAY);
        gc.fillRect(gridX, gridY + CoordHelper.cellMinusEdge(), CoordHelper.cellSize(), CoordHelper.edgeSize());
        gc.fillRect(gridX + CoordHelper.cellMinusEdge(), gridY, CoordHelper.edgeSize(), CoordHelper.cellSize());
        // lighter edges from top-left corner
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(gridX, gridY, CoordHelper.cellSize(), CoordHelper.edgeSize());
        gc.fillRect(gridX, gridY, CoordHelper.edgeSize(), CoordHelper.cellSize());
        // darker edges pointy corners
        gc.setFill(Color.GRAY);
        gc.fillPolygon(
                new double[]{gridX + CoordHelper.cellMinusEdge(), gridX + CoordHelper.cellSize(), gridX + CoordHelper.cellSize()},
                new double[]{gridY + CoordHelper.edgeSize(), gridY, gridY + CoordHelper.edgeSize()},
                3
        );
        gc.fillPolygon(
                new double[]{gridX, gridX + CoordHelper.edgeSize(), gridX + CoordHelper.edgeSize()},
                new double[]{gridY + CoordHelper.cellSize(), gridY + CoordHelper.cellMinusEdge(), gridY + CoordHelper.cellSize()},
                3
        );
    } // end drawFullSquare()
    
    public void drawEmptySquare(int cellX, int cellY) {
        double gridX = CoordHelper.cellToGrid(cellX);
        double gridY = CoordHelper.cellToGrid(cellY);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(gridX, gridY, CoordHelper.cellSize(), CoordHelper.cellSize());
        gc.setFill(Color.GRAY);
        gc.fillRect(gridX, gridY, CoordHelper.cellSize(), CoordHelper.edgeSize());
        gc.fillRect(gridX, gridY, CoordHelper.edgeSize(), CoordHelper.cellSize());
    } // end drawEmptySquare()
    
    public void drawMine(int cellX, int cellY) {
        gc.setFill(Color.BLACK);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    public void drawFlag(int cellX, int cellY) {
        gc.setFill(Color.RED);
        dot(CoordHelper.findFullCenter(cellX), CoordHelper.findFullCenter(cellY));
    }
    
    public void drawNumber(int cellX, int cellY, int number) {
        if (number >= 1 && number <= 8) {
            switch (number) {
                case 1:
                    drawOne(cellX, cellY);
                    break;
                case 2:
                    drawTwo(cellX, cellY);
                    break;
                case 3:
                    drawThree(cellX, cellY);
                    break;
                case 4:
                    drawFour(cellX, cellY);
                    break;
                case 5:
                    drawFive(cellX, cellY);
                    break;
                case 6:
                    drawSix(cellX, cellY);
                    break;
                case 7:
                    drawSeven(cellX, cellY);
                    break;
                case 8:
                    drawEight(cellX, cellY);
            } // end switch
        } else {
            throw new IllegalArgumentException("number must be between 1 and 8.");
        } // end if
    } // end drawNumber()
    
    private void drawOne(int cellX, int cellY) {
        gc.setFill(Color.BLUE);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawTwo(int cellX, int cellY) {
        gc.setFill(Color.GREEN);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawThree(int cellX, int cellY) {
        gc.setFill(Color.RED);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawFour(int cellX, int cellY) {
        gc.setFill(Color.DARKBLUE);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawFive(int cellX, int cellY) {
        gc.setFill(Color.DARKRED);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawSix(int cellX, int cellY) {
        gc.setFill(Color.CYAN);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawSeven(int cellX, int cellY) {
        gc.setFill(Color.PINK);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void drawEight(int cellX, int cellY) {
        gc.setFill(Color.DARKMAGENTA);
        dot(CoordHelper.findEmptyCenter(cellX), CoordHelper.findEmptyCenter(cellY));
    }
    
    private void dot(double gridX, double gridY) {
        gc.fillRect(gridX, gridY, 3.5, 3.5);
    }
} // end class GridHelper
