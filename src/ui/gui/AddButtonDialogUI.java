package ui.gui;

import javax.swing.*;
import java.awt.*;

public class AddButtonDialogUI { // TODO to extend or to not extend
     private final int DIALOG_WIDTH = 400;
     private final int DIALOG_HEIGHT = 250;

     private final int RIGID_DISTANCE = 25;

     private JDialog dialog;

     // Main components (those that will be added directly to the dialog)
    JPanel typePanel;
    JPanel amountPanel;
    JPanel descriptionPanel;
    JPanel genrePanel;
    JButton addButton;

    public AddButtonDialogUI(JFrame parent) {
        dialog = new JDialog(parent, "Add a Transaction", true);
        dialog.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);

        createComponents(dialog.getContentPane());

        dialog.pack();
        dialog.setVisible(true);
    }

    // EFFECTS: creates and adds components to the dialog's container
    private void createComponents(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        // initialize main components
        initializeTypePanel();
        initializeAmountPanel();
        initializeDescriptionPanel();
        initializeGenrePanel();
        initializeAddButton();

        // add main components + invisible fillers
        container.add(Box.createRigidArea(new Dimension(0,RIGID_DISTANCE)));
        container.add(typePanel);
        container.add(Box.createVerticalGlue());
        container.add(amountPanel);
        container.add(Box.createVerticalGlue());
        container.add(descriptionPanel);
        container.add(Box.createVerticalGlue());
        container.add(genrePanel);
        container.add(Box.createVerticalGlue());
        container.add(addButton);
        container.add(Box.createRigidArea(new Dimension(0,RIGID_DISTANCE)));
    }

    private void initializeTypePanel() {
        typePanel = new JPanel();
        BoxLayout layout = new BoxLayout(typePanel, BoxLayout.X_AXIS);
        typePanel.setLayout(layout);
        typePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Transaction Type:");
        JTextField textField = new JTextField("This should be a combo box");

        addPanelComponents(typePanel, label, textField);

    }

    private void initializeAmountPanel() {
        amountPanel = new JPanel();
        BoxLayout layout = new BoxLayout(amountPanel, BoxLayout.X_AXIS);
        amountPanel.setLayout(layout);
        amountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Amount:");
//        label.setSize(new Dimension(75,35)); // TODO don't know how to set sizes dynamically so i dont need to put values but rather constants
        JTextField textField = new JTextField("Enter transaction value here");
//        textField.setSize(new Dimension(75,35));

        addPanelComponents(amountPanel, label, textField);
    }

    private void initializeDescriptionPanel() {
        descriptionPanel = new JPanel();
        BoxLayout layout = new BoxLayout(descriptionPanel, BoxLayout.X_AXIS);
        descriptionPanel.setLayout(layout);
        descriptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Description:");
        JTextField textField = new JTextField("Enter description here");

        addPanelComponents(descriptionPanel, label, textField);
    }

    private void initializeGenrePanel() {
        genrePanel = new JPanel();
        BoxLayout layout = new BoxLayout(genrePanel, BoxLayout.X_AXIS);
        genrePanel.setLayout(layout);
        genrePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Transaction Genre:");
        JTextField textField = new JTextField("There should be a combo box here");

        addPanelComponents(genrePanel, label, textField);
    }

    private void initializeAddButton() {
        addButton = new JButton("Add");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: adds given label and component to the given panel, with appropriate invis filler space
    private void addPanelComponents(JPanel panel, JLabel label, JComponent inputComponent) {
        panel.add(Box.createRigidArea(new Dimension(RIGID_DISTANCE,0)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(inputComponent);
        panel.add(Box.createRigidArea(new Dimension(RIGID_DISTANCE,0)));
    }
}
