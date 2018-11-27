package ui.gui.dialogs;

import model.BudgetManager;
import model.enums.ExpGenre;

import javax.swing.*;

public class AddExpenseDialog extends AddButtonDialog {

    public AddExpenseDialog(JFrame parent, BudgetManager bm) {
        super(parent, "Add an Expense", bm);
    }

    @Override
    protected void initializeComboBox() {
        genreComboBox = new JComboBox(ExpGenre.values());
    }

}
