package DrawLib.Frames.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SubmitButton extends JPanel {
    private JButton btn;

    public SubmitButton(String btnHint) {
        setSize(320, 35);
        btn = new JButton(btnHint);
        btn.setBounds(((getWidth()-10)/2) - 50, 5, 100, 30);
        add(btn);
    }

    public void addActionListener(ActionListener actionListener) {
        btn.addActionListener(actionListener);
    }
}
