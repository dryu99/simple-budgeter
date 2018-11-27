package ui.gui.dialogs;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.RevGenre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRevenueButtonDialog extends AddButtonDialog {

    public AddRevenueButtonDialog(JFrame frame, BudgetManager bm) {
        super(frame, "Add a Revenue", bm);
    }

    @Override
    protected void initializeComboBox() {
        genreComboBox = new JComboBox(RevGenre.values());
    }

    // TODO can reduce coupling again here by only implementing the genreComboBox returning line and budgetmanager.addTrans one
    @Override
    protected void initializeAddlistener(JButton addButton) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //TODO how to set up listeners cleanly? just create one action listener that listens to all action events from this one button panel, opposed to one lisetner for each?
                double amount = Double.parseDouble(amountTextField.getText());
                String description = descriptionTextField.getText();
                RevGenre genre = (RevGenre) genreComboBox.getSelectedItem();

                budgetManager.addTransaction(new Transaction(amount, description, //TODO have to notify the table to add this transaction
                        genre, new SimpleDate(2018, 6, 20)));

                dialog.setVisible(false);
                dialog.dispose();
            }
        });
    }
}
