package ui.gui.dialogs;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.RevGenre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRevenueButtonDialog {

    private JDialog dialog;

     // Main components (those that will be added directly to the dialog)
    private JLabel dateLabel; // TODO implement date functionality
    private JLabel amountLabel;
    private JLabel descriptionLabel;
    private JLabel genreLabel;
    private JTextField amountTextField;
    private JTextField descriptionTextField;
    private JComboBox genreComboBox;
    private JPanel dialogButtonPanel;

    // Data models
    private BudgetManager budgetManager;

    public AddRevenueButtonDialog(JFrame parent, BudgetManager bm) {
        budgetManager = bm;

        dialog = new JDialog(parent, "Add a Revenue", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        createComponents(dialog.getContentPane());

        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    // EFFECTS: creates and adds components to the dialog's container
    private void createComponents(Container container) {
        container.setLayout(new GridBagLayout());

        initializeLabels();
        initializeTextFields();
        initializeComboBox();
        initializeButtonPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(8,8,8,8);

        container.add(amountLabel, gbc);
        gbc.gridy++;
        container.add(descriptionLabel, gbc);
        gbc.gridy++;
        container.add(genreLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1; //TODO might need this later

        container.add(amountTextField, gbc);
        gbc.gridy++;
        container.add(descriptionTextField, gbc);
        gbc.gridy++;
        container.add(genreComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(dialogButtonPanel, gbc);
    }

    private void initializeLabels() {
        amountLabel = new JLabel("Amount: ");
        descriptionLabel = new JLabel("Description: ");
        genreLabel = new JLabel("Genre: ");
    }

    private void initializeTextFields() {
        amountTextField = new JTextField(10);
        descriptionTextField = new JTextField(10);
    }

    private void initializeComboBox() {
        genreComboBox = new JComboBox(RevGenre.values());
    }

    // EFFECTS: initializes dialog button panel as well as action listeners
    private void initializeButtonPanel() {
        dialogButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,4,4));

        JButton addButton = new JButton("Add");
        addButton.setSelected(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //TODO how to set up listeners cleanly? just create one action listener that listens to all action events from this one button panel, opposed to one lisetner for each?
                double amount = Double.parseDouble(amountTextField.getText());
                String description = descriptionTextField.getText();
                RevGenre genre = (RevGenre) genreComboBox.getSelectedItem();

                budgetManager.addTransaction(new Transaction(amount, description, //TODO have to notify the table to add this transaction
                        genre, new SimpleDate(2018, 5, 20)));

                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        dialogButtonPanel.add(cancelButton);
        dialogButtonPanel.add(addButton);
    }

}
