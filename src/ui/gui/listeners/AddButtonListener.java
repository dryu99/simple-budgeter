package ui.gui.listeners;

import ui.gui.SimpleBudgeterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    private SimpleBudgeterUI mainUI;
    private JFrame mainFrame;

    public AddButtonListener(SimpleBudgeterUI ui) {
        this.mainUI = ui;
        mainFrame = ui.getFrame();
    }

    // TODO: creates a popup window here
    // TODO: also need to make it so non of the other buttons can be pressed when this frame is active
    @Override
    public void actionPerformed(ActionEvent ae) {
//        AddingEntryFrame adddingEntryFrame = new AddingEntryFrame();
//        adddingEntryFrame.setVisible(true);
//
//        mainFrame.add(adddingEntryFrame);
//        try {
//            adddingEntryFrame.setSelected(true);
//        } catch (PropertyVetoException e) { }
    }

}
