package ui.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    // Main components
    private JButton deleteButton;
    private JButton editButton;
    private JButton addRevenueButton;
    private JButton addExpenseButton;

    public ButtonPanel() {
        super(new GridLayout(1,4));
        createComponents();
    }

    // Getters (only for those components that may be modified/accessed):
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getAddRevenueButton() { return addRevenueButton; }
    public JButton getAddExpenseButton() { return addExpenseButton; }

    // MODIFIES: //TODO: what put for mod
    // EFFECTS: creates and adds components for this panel
    private void createComponents() {
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        addRevenueButton = new JButton("Add a Revenue");
        addExpenseButton = new JButton("Add an Expense");

        add(deleteButton);
        add(editButton);
        add(addRevenueButton);
        add(addExpenseButton);
    }
}
