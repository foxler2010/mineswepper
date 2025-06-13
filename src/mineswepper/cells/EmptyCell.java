package mineswepper.cells;

public class EmptyCell extends Cell {
    private final Cell topLeft;
    private final Cell topCenter;
    private final Cell topRight;
    private final Cell centerLeft;
    private final Cell centerRight;
    private final Cell bottomLeft;
    private final Cell bottomCenter;
    private final Cell bottomRight;
    
    public EmptyCell(
            Cell topLeft,
            Cell topCenter,
            Cell topRight,
            Cell centerLeft,
            Cell centerRight,
            Cell bottomLeft,
            Cell bottomCenter,
            Cell bottomRight) {
        this.topLeft = topLeft;
        this.topCenter = topCenter;
        this.topRight = topRight;
        this.centerLeft = centerLeft;
        this.centerRight = centerRight;
        this.bottomLeft = bottomLeft;
        this.bottomCenter = bottomCenter;
        this.bottomRight = bottomRight;
    }
    
//    @Override
//    public void clear() {
//        super.clear();
//        if (topLeft != null) {
//            topLeft.clear();
//        }
//        if (topCenter != null) {
//            topCenter.clear();
//        }
//        if (topRight != null) {
//            topRight.clear();
//        }
//        if (centerLeft != null) {
//            centerLeft.clear();
//        }
//        if (centerRight != null) {
//            centerRight.clear();
//        }
//        if (bottomLeft != null) {
//            bottomLeft.clear();
//        }
//        if (bottomCenter != null) {
//            bottomCenter.clear();
//        }
//        if (bottomRight != null) {
//            bottomRight.clear();
//        }
//    }
} // end class EmptyCell
