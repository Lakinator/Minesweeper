package DrawLib.Drawables;

import java.awt.*;

public class DrawableText {
    private int x, y;
    private String text;
    private Color color;

    public DrawableText(DrawableText drawableText) {
        this(drawableText.x, drawableText.y, drawableText.text, drawableText.color);
    }

    public DrawableText(int x, int y, String text) {
        this(x, y, text, Color.BLACK);
    }

    public DrawableText(int x, int y, String text, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
