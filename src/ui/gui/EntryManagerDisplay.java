package ui.gui;

import model.BudgetManager;
import ui.gui.data_models.TransactionTableModel;

import javax.swing.*;
import java.awt.*;

public class EntryManagerDisplay extends JPanel {
    private JTable revenueTable;
    private JTable expenseTable;
    private JPanel revenueTablePanel;
    private JPanel expenseTablePanel;
    private JPanel displayPanel;

    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;
    private JLabel statsLabel;

//    private JList monthList;
    private BudgetManager budgetManager;

    public EntryManagerDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());

        revenueTableModel = ui.getRevenueTableModel();
        expenseTableModel = ui.getExpenseTableModel();
        statsLabel = ui.getStatsLabel();

        budgetManager = ui.getBudgetManager();

        createComponents();
    }

    // MODIFIES:
    // EFFECTS: creates and adds components for the panel
    private void createComponents() { //TODO should i split up methods like i did in SimpleBudgeterUI
        initializeTables();
        initializeTablePanels();
        initializeDisplayPanel();
        initializeStatsLabel();

        add(displayPanel);
        add(statsLabel, BorderLayout.SOUTH);
    }

    private void initializeStatsLabel() {
        // Initialize JLabel
//        statsLabel = new JLabel("Statistics"); // TODO have to have this updated at the start of program (HOWEVER HTIS REQUIRES THE NEED FOR MONTHUILIST WHICH IS OUTSIDE IN SIMPLEBUDGETERUI AND I AVOIDED PASSING IT IN ORIGINALLY BUTNOW HWOHEIWOFAHEIOA)
        statsLabel.setText("Statistics");
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        statsLabel.setPreferredSize(new Dimension(0,75));
    }

    // EFFECTS: initializes transaction tables
    private void initializeTables() {
        // Initialize JTable for revenues
        revenueTable = new JTable(revenueTableModel);
        revenueTable.setFillsViewportHeight(true);
        revenueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // TODO: make these blocks into intialize methods?
        revenueTable.setGridColor(Color.BLACK);
        // TODO: have to set customized renderer

        // Initialize JTable for expenses
        expenseTable = new JTable(expenseTableModel);
        expenseTable.setFillsViewportHeight(true);
        expenseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseTable.setGridColor(Color.BLACK);
        // TODO: have to set customized renderer

        // TODO: idk what the optimal way to do this would be (init tables first or table panels) or just have the tables be global fields
    }

    // EFFECTS: initializes panels for the given transaction tables
    private void initializeTablePanels() {
        // Init JPanel for transaction tables
        revenueTablePanel = new JPanel(new BorderLayout());
        expenseTablePanel = new JPanel(new BorderLayout());

        // Init JLabel headers
        JLabel revHeader = new JLabel("Revenues");
        JLabel expHeader = new JLabel("Expenses");
        revHeader.setHorizontalAlignment(JLabel.CENTER);
        expHeader.setHorizontalAlignment(JLabel.CENTER);
        revHeader.setPreferredSize(new Dimension(0,50));
        expHeader.setPreferredSize(new Dimension(0,50));

        // create table panels with scroll panes
        revenueTablePanel.add(revHeader, BorderLayout.NORTH);
        revenueTablePanel.add(new JScrollPane(revenueTable));

        expenseTablePanel.add(expHeader, BorderLayout.NORTH);
        expenseTablePanel.add(new JScrollPane(expenseTable));
    }

    // EFFECTS: Initialize panel for transaction table panels
    private void initializeDisplayPanel() {
        displayPanel = new JPanel(new GridLayout(1,2,25,0));
        displayPanel.add(revenueTablePanel);
        displayPanel.add(expenseTablePanel);
    }




}
