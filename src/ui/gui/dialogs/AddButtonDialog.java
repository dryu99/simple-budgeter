package ui.gui.dialogs;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.Genre;
import model.exceptions.EmptyDescriptionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddButtonDialog {

    protected JDialog dialog;

    // Main components (those that will be added directly to the dialog)
    private JLabel dateLabel; // TODO implement date functionality
    private JLabel amountLabel;
    private JLabel descriptionLabel;
    private JLabel genreLabel;
    private JTextField dateTextField; // TODO this should be a panel of combo boxes
    private JPanel dateComboBoxPanel;
    private JTextField amountTextField;
    private JTextField descriptionTextField;
    protected JComboBox genreComboBox;
    private JPanel dialogButtonPanel;

    // Data models
    private BudgetManager budgetManager;

    public AddButtonDialog(JFrame parent, String title, BudgetManager bm) {
        budgetManager = bm;

        dialog = new JDialog(parent, title, true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        createComponents(dialog.getContentPane());

        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

    }

    // EFFECTS: creates and adds components to the dialog's container
    protected void createComponents(Container container) {
        container.setLayout(new GridBagLayout());

        initializeLabels();
        initializeTextFields();
        initializeGenreComboBox();
        initializeButtonPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(8,8,8,8);

        container.add(dateLabel, gbc);
        gbc.gridy++;
        container.add(amountLabel, gbc);
        gbc.gridy++;
        container.add(descriptionLabel, gbc);
        gbc.gridy++;
        container.add(genreLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1; //TODO might need this later

        container.add(dateTextField, gbc);
        gbc.gridy++;
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
        dateLabel = new JLabel("Date: ");
        amountLabel = new JLabel("Amount: ");
        descriptionLabel = new JLabel("Description: ");
        genreLabel = new JLabel("Genre: ");
    }

    private void initializeTextFields() {
//        dateTextField = new JTextField(10);
        amountTextField = new JTextField(10);
        descriptionTextField = new JTextField(10);
    }

//    private void initializeDateComboBoxPanel() {
//        dateComboBoxPanel = new JPanel(new GridLayout(0,3));
//
//        String[] dayArray =
//
//    }

    // MODIFIES: this
    // EFFECTS: initializes combo box according to actual type
    protected abstract void initializeGenreComboBox();

    // TODO this methods so fat maybe separate into methods
    // EFFECTS: initializes dialog button panel as well as action listeners
    private void initializeButtonPanel() {
        dialogButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,4,4));

        JButton addButton = new JButton("Add");
        addButton.setSelected(true);
        addButton.addActionListener(new ActionListener() {
            // EFFECTS: if given user amount is invalid, show error message dialog
            //          ow, add transaction to budget manager based on given user inputs and close dialog
            @Override
            public void actionPerformed(ActionEvent e) { //TODO how to set up listeners cleanly? just create one action listener that listens to all action events from this one button panel, opposed to one lisetner for each?
                try {
                    double amount = Double.parseDouble(amountTextField.getText());
                    String description = descriptionTextField.getText();
                    Genre genre = (Genre) genreComboBox.getSelectedItem();

                    // Check for empty description
                    if (description.trim().isEmpty()) { throw new EmptyDescriptionException(); }

                    // Adjust amount according to transaction type
                    if (genre instanceof ExpGenre) { amount *= -1; }

                    budgetManager.addTransaction(new Transaction(amount, description, //TODO have to notify the table to add this transaction
                            genre, new SimpleDate(2018, 6, 20)));

                    dialog.setVisible(false);
                    dialog.dispose();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please type in a valid number in the 'amount' box.",
                            "Invalid Transaction Amount Error",
                            JOptionPane.ERROR_MESSAGE);
                    amountTextField.grabFocus();
                } catch (EmptyDescriptionException ede) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please type in a description",
                            "Empty Description Error",
                            JOptionPane.ERROR_MESSAGE);
                    descriptionTextField.grabFocus();
                }

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
