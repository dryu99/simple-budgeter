package ui.gui.listeners;

import ui.gui.AddButtonDialogUI;
import ui.gui.SimpleBudgeterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    // Components to be accessed
    private JFrame mainFrame;

    public AddButtonListener(SimpleBudgeterUI ui) {
        mainFrame = ui.getFrame();
    }

    // TODO make a pop up adding frame
    // EFFECTS: creates a dialog frame that prompts user for new transaction input
    @Override
    public void actionPerformed(ActionEvent ae) {
//        JOptionPane.showMessageDialog(mainFrame,"Adding a transaction!!!");
        AddButtonDialogUI addButtonDialog = new AddButtonDialogUI(mainFrame);

    }

}
