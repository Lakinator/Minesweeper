package DrawLib.Drawables;

import java.awt.Color;

public class Rect {
    private int x, y;
    private int width, height;
    private boolean filled;
    private Color color;

    public Rect(Rect rect) {
        this(rect.x, rect.y, rect.width, rect.height, rect.filled, rect.color);
    }

    public Rect(int x, int y, int width, int height) {
        this(x, y, width, height, true, Color.DARK_GRAY);
    }

    public Rect(int x, int y, int width, int height, boolean filled, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filled = filled;
        this.color = color;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + width);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
