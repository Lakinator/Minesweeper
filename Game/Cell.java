package Game;

import DrawLib.Frames.DrawFrame;
import DrawLib.Drawables.Circle;
import DrawLib.Drawables.DrawableText;
import DrawLib.Drawables.Rect;

import java.awt.*;

public class Cell extends Rect {
    private int rowPos, columnPos;
    private boolean revealed;
    private boolean isBomb;
    private int bombCount;
    private DrawableText bombCountText;
    private Circle bombCircle;

    public Cell(int x, int y, int width, int height, int rowPos, int columnPos) {
        this(x, y, width, height, rowPos, columnPos, true, Color.DARK_GRAY);
    }

    public Cell(int x, int y, int width, int height, int rowPos, int columnPos, boolean filled, Color color) {
        super(x, y, width, height, filled, color);
        this.revealed = false;
        this.isBomb = false;
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.bombCount = 0;
        this.bombCountText = new DrawableText(getX() + (int)(getWidth()/2.4), getY() + (int)(getWidth()/1.6), "" + bombCount);
        this.bombCircle = new Circle(getX(), getY(), getWidth(), true, Color.RED);
    }

    public void reveal(DrawFrame frame, Grid grid) {
        if (revealed) return;
        else revealed = true;

        setFilled(true);

        if (isBomb) {
            setColor(Color.GRAY);
            frame.draw(bombCircle);
        } else {
            setColor(Color.LIGHT_GRAY);
            if (updateBombCount(grid) > 0) {
                frame.draw(bombCountText);
            } else {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (rowPos + i < 0 || rowPos + i >= grid.getRowCount()) continue;
                        if (columnPos + j < 0 || columnPos + j >= grid.getColumnCount()) continue;

                        grid.getCell(rowPos + i, columnPos + j).reveal(frame, grid);
                    }
                }
            }
        }

    }

    public int updateBombCount(Grid grid) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (rowPos + i < 0 || rowPos + i >= grid.getRowCount()) continue;
                if (columnPos + j < 0 || columnPos + j >= grid.getColumnCount()) continue;

                if (grid.getCell(rowPos + i, columnPos + j).isBomb) {
                    bombCount++;
                }
            }
        }

        bombCountText.setText("" + bombCount);
        return bombCount;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public int getBombCount() {
        return bombCount;
    }

    public DrawableText getBombCountText() {
        return bombCountText;
    }

    public Circle getBombCircle() {
        return bombCircle;
    }
}
