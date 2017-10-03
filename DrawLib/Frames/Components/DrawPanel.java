package DrawLib.Frames.Components;

import DrawLib.Drawables.Circle;
import DrawLib.Drawables.DrawableText;
import DrawLib.Drawables.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Circle> circles;
    private ArrayList<Rect> rects;
    private ArrayList<DrawableText> texts;

    public DrawPanel() {
        circles = new ArrayList<>();
        rects = new ArrayList<>();
        texts = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Rect rect : rects) {
            g2d.setColor(rect.getColor());
            if (rect.isFilled()) {
                g2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getWidth());
            } else {
                g2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getWidth());
            }
        }

        for (Circle circle : circles) {
            g2d.setColor(circle.getColor());
            if (circle.isFilled()) {
                g2d.fillOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
            } else {
                g2d.drawOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
            }
        }

        for (DrawableText text : texts) {
            g2d.setColor(text.getColor());
            g2d.drawString(text.getText(), text.getX(), text.getY());
        }

    }

    public void add(Circle circle) {
        circles.add(new Circle(circle));
    }

    public void add(Rect rect) {
        rects.add(new Rect(rect));
    }

    public void add(DrawableText text) {
        texts.add(new DrawableText(text));
    }

    public void clear() {
        circles.clear();
        rects.clear();
        texts.clear();
    }

}
