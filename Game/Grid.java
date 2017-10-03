package Game;

public class Grid {
    private Cell[][] cells;
    private int cellCount;
    private int cellSize;

    public Grid(int rows, int colums, int cellSize) {
        cells = new Cell[rows][colums];
        cellCount = rows * colums;
        this.cellSize = cellSize;
    }


    public boolean allRevealed(int bombCount) {
        int revealed = 0;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (cells[i][j].isRevealed() && !cells[i][j].isBomb()) revealed++;
            }
        }

        return revealed == getCellCount() - bombCount;
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(int row, int column, Cell cell) {
        cells[row][column] = cell;
    }

    public int getCellCount() {
        return cellCount;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getRowCount() {
        return cells.length;
    }

    public int getColumnCount() {
        return cells[0].length;
    }
}
