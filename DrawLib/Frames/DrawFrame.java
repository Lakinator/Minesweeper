package DrawLib.Frames;

import DrawLib.Drawables.Circle;
import DrawLib.Drawables.DrawableText;
import DrawLib.Drawables.Rect;
import DrawLib.Frames.Components.DrawPanel;

import javax.swing.*;
import java.awt.event.MouseListener;

public class DrawFrame extends JFrame {
    private DrawPanel dp;

    public DrawFrame(String title) {
        this(title, 800, 600);
    }

    public DrawFrame(String title, int width, int height) {
        super(title);
        super.setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        dp = new DrawPanel();
        dp.setBounds(0, 0, width, height);
        add(dp);
    }

    @Override
    public void addMouseListener(MouseListener m) {
        dp.addMouseListener(m);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        dp.setBounds(0, 0, width, height);
    }

    public void draw(Circle... circles) {
        for (Circle c : circles) dp.add(c);
        dp.repaint();
    }

    public void draw(Rect... rects) {
        for (Rect s : rects) dp.add(s);
        dp.repaint();
    }

    public void draw(DrawableText... texts) {
        for (DrawableText text : texts)  dp.add(text);
        dp.repaint();
    }

    public void clearScreen() {
        dp.clear();
        dp.repaint();
    }

}
