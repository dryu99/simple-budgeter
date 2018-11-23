package ui.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    // Main components
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;

    public ButtonPanel() {
        super(new GridLayout(1,3));
        createComponents();
    }

    // Getters (only for those components that may be modified/accessed):
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getAddButton() { return addButton; }

    // MODIFIES: //TODO: what put for mod
    // EFFECTS: creates and adds components for this panel
    private void createComponents() {
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        addButton = new JButton("Add");

        add(deleteButton);
        add(editButton);
        add(addButton);
    }
}
