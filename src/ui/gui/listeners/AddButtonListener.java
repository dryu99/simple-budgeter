package ui.gui.listeners;

import ui.gui.SimpleBudgeterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    private JFrame mainFrame;

    public AddButtonListener(SimpleBudgeterUI ui) {
        mainFrame = ui.getFrame();
    }

    // TODO make a pop up adding frame
    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(mainFrame,"Adding a transaction!!!");
    }

}
