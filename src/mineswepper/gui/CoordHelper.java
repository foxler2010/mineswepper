package mineswepper.gui;

public class CoordHelper {
    /*
     * Settings that are changeable
     */
    
    /**
     * The size of each cell in pixels (or whatever the canvas' unit is)
     */
    private static final double CELL_SIZE = 30;
    
    /**
     * The ratio of edge size to cell size.
     * <p>
     * For example, a value of 12 would mean you can fit 12 edges within one cell,
     * and that each edge is 1/12th the size of the cell.
     */
    private static final double EDGE_RATIO = 12;
    
    /*
     * Values based off of the above settings
     */
    
    /**
     * The size of the edges, calculated using the size of the cell and the edge ratio,
     * which describes how much of the cell is part of the edge.
     */
    private static final double EDGE_SIZE = CELL_SIZE / EDGE_RATIO;
    
    /**
     * This is the size of the cell minus the size of one edge.
     * It is used as a coordinate for building the edges.
     */
    private static final double CELL_MINUS_EDGE = CELL_SIZE - EDGE_SIZE;
    
    /**
     * Returns the grid coordinate of the given cell.
     *
     * @param cellCoordinate The x or y coordinate of the cell in the cell coordinate system
     * @return The coordinate of either the top or left side of the cell
     *         (depending on if it's x or y being converted)
     */
    public static double cellToGrid(int cellCoordinate) {
        return cellCoordinate * CELL_SIZE;
    }
    
    /**
     * Returns the cell coordinate of the cell that the given point is in.
     *
     * @param gridCoordinate The x or y coordinate of a point which resides within a certain cell.
     * @return The x or y cell coordinate of the certain cell that the point is in.
     */
    public static int gridToCell(double gridCoordinate) {
        // first divide, which gives us a "fractional cellCoordinate",
        // then cast to int, which rounds down to whatever cell the gridCoordinate is in.
        return (int) (gridCoordinate / CELL_SIZE);
    }
    
    public static double cellSize() {
        return CELL_SIZE;
    }
    
    public static double edgeSize() {
        return EDGE_SIZE;
    }
    
    public static double cellMinusEdge() {
        return CELL_MINUS_EDGE;
    }
    
    /**
     * Returns the x or y coordinate of the center of an empty cell.
     * This method should be used when positioning something in the center of an empty cell.
     * For full cells (ie "raised cells"), use findFullCenter().
     *
     * @param cellCoord The x or y coord of an empty cell in the cell coordinate system.
     * @return The x or y coordinate of the center of the empty cell.
     */
    public static double findEmptyCenter(int cellCoord) {
        return cellToGrid(cellCoord) + (CELL_SIZE / 2);
    }
    
    /**
     * Returns the x or y coordinate of the center of a full cell.
     * This method should be used when positioning something in the center of a full cell.
     * For empty cells, use findEmptyCenter().
     *
     * @param cellCoord The x or y coord of a full cell in the cell coordinate system.
     * @return The x or y coordinate of the center of the full cell.
     */
    public static double findFullCenter(int cellCoord) {
        return cellToGrid(cellCoord) + ((CELL_SIZE - EDGE_SIZE) / 2);
    }
} // end class CoordHelper
