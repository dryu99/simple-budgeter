package ui.gui;

import ui.gui.listeners.AddButtonListener;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;

    private SimpleBudgeterUI mainUI;

    public ButtonPanel(SimpleBudgeterUI ui) {
        super(new GridLayout(1,3));

        this.mainUI = ui;

        createComponents();
    }

    // MODIFIES: //TODO: what put for mod
    // EFFECTS: creates and adds components for this panel
    private void createComponents() {
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        addButton = new JButton("Add");

        // TODO: have to add listeners to buttons
        addButton.addActionListener(new AddButtonListener(mainUI));

        this.add(deleteButton);
        this.add(editButton);
        this.add(addButton);
    }
}
