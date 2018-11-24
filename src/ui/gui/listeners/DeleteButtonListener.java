package ui.gui.listeners;

import ui.gui.SimpleBudgeterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    // Components to be accessed
    private JFrame mainFrame;

    // Data models
    private Object[] options = {"Yes", "No"};

    public DeleteButtonListener(SimpleBudgeterUI ui) {
        mainFrame = ui.getFrame();
    }


    // EFFECTS: creates a dialog mainFrame that confirms with user if they want to delete the selected transaction //TODO and entry?
    @Override
    public void actionPerformed(ActionEvent e) {

        JOptionPane.showOptionDialog(mainFrame,"Are you sure you want to delete this transaction?",
                "Deleting Transaction",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

    }
}
