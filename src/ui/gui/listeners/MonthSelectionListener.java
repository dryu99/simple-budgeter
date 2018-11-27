package ui.gui.listeners;

import model.BudgetManager;
import ui.gui.SimpleBudgeterUI;
import ui.gui.data_models.TransactionTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Observable;

// TODO maybe dont make a sep class, put in monthListDisplay class
public class MonthSelectionListener extends Observable implements ListSelectionListener {
    // Components to be modified
    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;
    private JLabel statsLabel;

    // Data models
    private BudgetManager budgetManager;

    public MonthSelectionListener(SimpleBudgeterUI ui) {
        revenueTableModel = ui.getEntryDisplay().getRevenueTableModel();
        expenseTableModel = ui.getEntryDisplay().getExpenseTableModel();
        statsLabel = ui.getEntryDisplay().getStatsLabel();
        budgetManager = ui.getBudgetManager();
    }

    // MODIFIES: // TODO: what to put for modifies
    // EFFECTS: updates necessary components that use data based on the current date selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
//        JList monthUIList = (JList) e.getSource();
//        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());
//
//        updateTableData(selectedDate);
//        updateStatisticsLabel(selectedDate);

        setChanged();
        notifyObservers();
    }

//    // EFFECTS: updates expense and revenue table data corresponding to the given date
//    private void updateTableData(String selectedDate) {
//        revenueTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true));
//        expenseTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));
//    }

//    // EFFECTS: updates statistics label to show proper net value corresponding to given date
//    private void updateStatisticsLabel(String selectedDate) { // TODO: implement observer pattern here? would get rid of components to be modified fields
//        statsLabel.setText("" + budgetManager.getNetValueFromDate(selectedDate));
//    }

}
