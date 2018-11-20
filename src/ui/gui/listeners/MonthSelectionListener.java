package ui.gui.listeners;

import model.BudgetManager;
import ui.gui.SimpleBudgeterUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MonthSelectionListener implements ListSelectionListener {
    private JList monthUIList;
    private JPanel entryManagerDisplay;
    private BudgetManager budgetManager;

    public MonthSelectionListener(SimpleBudgeterUI ui) {
        monthUIList = ui.getMonthUIList();
        entryManagerDisplay = ui.getEntryManagerDisplay();
        budgetManager = ui.getBudgetManager();
    }

    // MODIFIES: // TODO: what to put for modifies
    // EFFECTS:
    @Override
    public void valueChanged(ListSelectionEvent e) {
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());

        updateLabel(budgetManager.getEntryManagerFromDate(selectedDate));
    }

    // tODO: should i declare this method in simplebudgeterui, and just call it from there
    // TODO: have it be updateTable
    private void updateLabel(Object o) {
    }
}
