package ui.gui.renderers;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.Genre;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DateCellRenderer extends DefaultTableCellRenderer{
    private BudgetManager budgetManager;

    public DateCellRenderer(BudgetManager bm) {
        budgetManager = bm;
    }

    // TODO the issue with this class is that there is prob a much easier way to get the description data from the specified transaction.
    // TOdO     however this would involve storing the description data somewhere in the transaction table model (or just somewhere in the ui)
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object date,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        JLabel component = (JLabel) super.getTableCellRendererComponent(table, date, isSelected, hasFocus, row, column);

        // Sets up necessary variables for searching for description
        SimpleDate simpleDate = (SimpleDate) date;
        String simpleFormattedDate = simpleDate.simpleFormat();
        Double amount = (Double) table.getValueAt(row,column + 1);
        Genre genre = (Genre) table.getValueAt(row,column + 2);

        Transaction completeTransaction = budgetManager.getCompleteTransactionFromDate(simpleFormattedDate,
                new Transaction(amount, genre, simpleDate));

        component.setToolTipText(completeTransaction.getDesc());

        return component;
    }
}
