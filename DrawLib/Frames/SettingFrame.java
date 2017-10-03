package DrawLib.Frames;

import DrawLib.Frames.Components.SettingInput;
import DrawLib.Frames.Components.SubmitButton;

import javax.swing.*;

public class SettingFrame extends JFrame {
    private int width, height;
    private int heightOffset = 29;


    public SettingFrame(String title) {
        super(title);

        width = 0;
        height = 0;

        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public void addInputSetting(SettingInput settingInput) {
        settingInput.setBounds(0, height, settingInput.getWidth(), settingInput.getHeight());
        add(settingInput);
        width = settingInput.getWidth();
        height += settingInput.getHeight();
        setSize(width, height + heightOffset);
    }

    public void addSubmitButton(SubmitButton submitButton) {
        submitButton.setBounds(0, height, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);
        width = submitButton.getWidth();
        height += submitButton.getHeight();
        setSize(width, height + heightOffset);
    }

}
