package ui.gui.listeners;

import model.BudgetManager;
import ui.gui.SimpleBudgeterUI;
import ui.gui.data_models.TransactionTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MonthSelectionListener implements ListSelectionListener {
    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;
    private BudgetManager budgetManager;

    public MonthSelectionListener(SimpleBudgeterUI ui) {
        revenueTableModel = ui.getRevenueTableModel();
        expenseTableModel = ui.getExpenseTableModel();
//        revenueTableModel = emd.getRevenueTableModel(); // TODO: this is turrible, i just want to pass ui around not any other components
        budgetManager = ui.getBudgetManager();
    }

    // MODIFIES: // TODO: what to put for modifies
    // EFFECTS: updates necessary components that use data based on the current date selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList monthUIList = (JList) e.getSource();
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());

        updateTableData(selectedDate);
        updateStatisticsLabel(selectedDate);
    }

    // EFFECTS: updates expense and revenue table data corresponding to the given date
    private void updateTableData(String selectedDate) {
        revenueTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true));
        expenseTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));
    }

    // EFFECTS: updates statistics label to show proper net value corresponding to given date
    private void updateStatisticsLabel(String selectedDate) { //tODO okay its getting really really annoying to pass around certain variables around now (Stats JLabel should be inside entry manager display only sigh)

    }

}
