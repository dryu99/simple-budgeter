package ui.gui.dialogs;

import model.BudgetManager;
import model.enums.RevGenre;

import javax.swing.*;

public class AddRevenueDialog extends AddButtonDialog {

    public AddRevenueDialog(JFrame frame, BudgetManager bm) {
        super(frame, "Add a Revenue", bm);
    }

    @Override
    protected void initializeComboBox() {
        genreComboBox = new JComboBox(RevGenre.values());
    }
}
