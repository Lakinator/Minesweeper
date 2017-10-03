package DrawLib.Drawables;

import java.awt.*;

public class Circle {
    private int x, y;
    private int diameter;
    private boolean filled;
    private Color color;

    public Circle(Circle circle) {
        this(circle.x, circle.y, circle.diameter, circle.filled, circle.color);
    }

    public Circle(int x, int y, int diameter) {
        this(x, y, diameter, true, Color.DARK_GRAY);
    }

    public Circle(int x, int y, int diameter, boolean filled, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.filled = filled;
        this.color = color;
    }

    public void isClicked(int mouseX, int mouseY) {
        //TODO: isClicked function
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

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
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
