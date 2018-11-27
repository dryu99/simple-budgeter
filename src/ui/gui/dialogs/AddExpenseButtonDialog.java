package ui.gui.dialogs;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.ExpGenre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseButtonDialog extends AddButtonDialog {

    public AddExpenseButtonDialog(JFrame parent, BudgetManager bm) {
        super(parent, "Add an Expense", bm);
    }

    @Override
    protected void initializeComboBox() {
        genreComboBox = new JComboBox(ExpGenre.values());
    }

    @Override
    protected void initializeAddlistener(JButton addButton) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //TODO how to set up listeners cleanly? just create one action listener that listens to all action events from this one button panel, opposed to one lisetner for each?
                double amount = Double.parseDouble(amountTextField.getText());
                String description = descriptionTextField.getText();
                ExpGenre genre = (ExpGenre) genreComboBox.getSelectedItem();

                budgetManager.addTransaction(new Transaction(amount * -1, description, //TODO have to notify the table to add this transaction
                        genre, new SimpleDate(2018, 6, 20)));

                dialog.setVisible(false);
                dialog.dispose();
            }
        });
    }

}
