package ui.gui;

import model.BudgetManager;
import ui.gui.data_models.TransactionTableModel;

import javax.swing.*;
import java.awt.*;

public class EntryManagerDisplay extends JPanel {
    private JTable revenueTable; //tODO: should i put every component taht exists in this panel (i.e. scroll pane too) or just have the ones that may be modified
    private JTable expenseTable;
    private JPanel tablePanel;
    private JLabel statsLabel;
    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;

//    private JList monthList;
    private BudgetManager budgetManager;

    public EntryManagerDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());

        revenueTableModel = ui.getRevenueTableModel();
        expenseTableModel = ui.getExpenseTableModel();

        budgetManager = ui.getBudgetManager();

        createComponents();
    }

    // MODIFIES:
    // EFFECTS: creates and adds components for the panel
    private void createComponents() { //TODO should i split up methods like i did in SimpleBudgeterUI

        // Initialize JTable for revenues
        revenueTable = new JTable(revenueTableModel);
        revenueTable.setFillsViewportHeight(true);
        revenueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        revenueTable.setGridColor(Color.BLACK);
        // TODO: have to set customized renderer

        // Initialize JTable for expenses
        expenseTable = new JTable(expenseTableModel);
        expenseTable.setFillsViewportHeight(true);
        expenseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseTable.setGridColor(Color.BLACK);
        // TODO: have to set customized renderer

        // Initialize JPanel for two tables
        tablePanel = new JPanel(new GridLayout(1,2,25,0));
        tablePanel.add(new JScrollPane(revenueTable));
        tablePanel.add(new JScrollPane(expenseTable)); // TODO: maybe add scroll panes to both?

        // Initialize JLabel
        statsLabel = new JLabel("Statistics");
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        statsLabel.setPreferredSize(new Dimension(0,75));

        add(tablePanel);
        add(statsLabel, BorderLayout.SOUTH);
    }




}
