package ui.gui;

import model.BudgetManager;

import javax.swing.*;
import java.awt.*;

public class MonthListDisplay extends JPanel {
    // Main components
    private JLabel header;
    private JList monthUIList;

    // Data models
    private DefaultListModel monthModel;
    private BudgetManager budgetManager;

    public MonthListDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());
        budgetManager = ui.getBudgetManager();

        createComponents();
    }

    // Getters (only for those components that may be modified/accessed):
    public JList getMonthUIList() { return monthUIList; }

    // MODIFIES: this
    // EFFECTS: creates and adds components for this panel
    private void createComponents() {
        // initialize main components
        initializeHeader();
        initializeMonthList();

        // add main components
        add(header, BorderLayout.NORTH);
        add(monthUIList);
    }

    // EFFECTS: initializes list header
    private void initializeHeader() {
        header = new JLabel("Months");
        header.setHorizontalAlignment(JLabel.CENTER);
    }

    // EFFECTS: initializes month ui list
    private void initializeMonthList() {
        monthModel = new DefaultListModel();
        for (String s : budgetManager) {
            monthModel.addElement(s);
        }

        monthUIList = new JList(monthModel);
        monthUIList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monthUIList.setSelectedIndex(0);
        monthUIList.setLayoutOrientation(JList.VERTICAL);
        monthUIList.setVisibleRowCount(-1);
    }

}