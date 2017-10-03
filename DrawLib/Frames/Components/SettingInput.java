package DrawLib.Frames.Components;

import javax.swing.*;

public class SettingInput extends JPanel {
    private JTextField inputField;
    private JLabel inputLabel;

    public SettingInput(String label, String hint) {
        setLayout(null);
        setSize(320, 30);
        inputLabel = new JLabel(label);
        inputLabel.setBounds(5, 5, 100, 20);
        inputField = new JTextField(hint);
        inputField.setBounds(105, 5, 200, 20);
        add(inputLabel);
        add(inputField);
    }

    public String getTextInput() {
        return inputField.getText();
    }

    public int getIntInput() {
        int val = 0;

        try {
            val = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException ex) {
            System.out.println(ex.toString());
        }

        return val;
    }

    public void setHint(String hint) {
        inputField.setText(hint);
    }

}
