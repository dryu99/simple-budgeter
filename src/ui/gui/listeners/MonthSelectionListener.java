package ui.gui.listeners;

import model.BudgetManager;
import ui.gui.SimpleBudgeterUI;
import ui.gui.data_models.EntryManagerTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MonthSelectionListener implements ListSelectionListener {
    private EntryManagerTableModel tableModel;
    private BudgetManager budgetManager;

    public MonthSelectionListener(SimpleBudgeterUI ui) {
        tableModel = ui.getTableModel();
//        tableModel = emd.getTableModel(); // TODO: this is turrible, i just want to pass ui around not any other components
        budgetManager = ui.getBudgetManager();
    }

    // MODIFIES: // TODO: what to put for modifies
    // EFFECTS: sets new Table data
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList monthUIList = (JList) e.getSource();
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());

        tableModel.setTableData(budgetManager.getAllTransactionsFromDate(selectedDate));
    }

}
