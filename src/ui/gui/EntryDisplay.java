package ui.gui;

import model.BudgetManager;
import ui.gui.data_models.TransactionTableModel;
import ui.gui.renderers.AmountCellRenderer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// TODO need to make thie class an observer with the update method updating the tablle models
// TODO observables will be monthListDisplay listener and budgetmanager (when transaction is added, notfiy observers)
public class EntryDisplay extends JPanel implements Observer {
    // Main components (those that will be added directly to the frame)
    private JPanel displayPanel;
    private JLabel statsLabel;

    // Sub components
    private JTable revenueTable;
    private JTable expenseTable;
    private JPanel revenueTablePanel;
    private JPanel expenseTablePanel;

    // Data models
    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;
    private BudgetManager budgetManager;

    // Components whose data needs to be accessed
    private JList monthUIList;

    public EntryDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());

        monthUIList = ui.getMonthListDisplay().getMonthUIList(); //TODO this is sketchy because if the monthlistdisplay isnt created before creating this, im gonn get a null pointer error.
                                                                // TODO the ONLY reason why i need monthui for this class is because i need to get the selection index to get the selected date. IF i could figure out a way to manually create action events, i think it should be solved
        budgetManager = ui.getBudgetManager();

        createComponents();
    }

    // Getters (only for those components that may be modified/accessed):
    public JLabel getStatsLabel() { return statsLabel; }
    public TransactionTableModel getRevenueTableModel() { return revenueTableModel; }
    public TransactionTableModel getExpenseTableModel() { return expenseTableModel; }

    // MODIFIES: // todo put this here?
    // EFFECTS: creates and adds components for the panel
    private void createComponents() { //TODO should i split up methods like i did in SimpleBudgeterUI
        // initialize main components
        initializeDisplayPanel();
        initializeStatsLabel();

        // add main components
        add(displayPanel);
        add(statsLabel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Initialize panel for transaction table panels
    private void initializeDisplayPanel() {
        initializeTablePanels();

        displayPanel = new JPanel(new GridLayout(1,2,25,0));
        displayPanel.add(revenueTablePanel);
        displayPanel.add(expenseTablePanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes panels for the given transaction tables
    private void initializeTablePanels() {
        initializeTables();

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

    // EFFECTS: initializes transaction tables
    private void initializeTables() {
        initializeTableModels();

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

        initializeCellRenderers();
    }

    // EFFECTS: initialize table models
    private void initializeTableModels() {
        int selectedIndex = monthUIList.getSelectedIndex();

        if (selectedIndex >= 0) {
            String selectedDate = budgetManager.getMonths().get(selectedIndex);

            revenueTableModel = new TransactionTableModel(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true)); //TODO how to invoke actionlistener events at the start of program manually
            expenseTableModel = new TransactionTableModel(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));
        }
        // TODO need to change this so when i initialize im just initializing a talbe update event, and i just initialize these two objects below
        revenueTableModel = new TransactionTableModel();
        expenseTableModel = new TransactionTableModel();
    }

    private void initializeCellRenderers() {
        // initialize amount renderers first
        AmountCellRenderer acr = new AmountCellRenderer();

        TableColumn revAmountColumn = revenueTable.getColumnModel().getColumn(1);
        revAmountColumn.setCellRenderer(acr);

        TableColumn expAmountColumn = expenseTable.getColumnModel().getColumn(1);
        expAmountColumn.setCellRenderer(acr);

    }

    // EFFECTS: initializes stats label
    private void initializeStatsLabel() {
        statsLabel = new JLabel("Statistics"); // TODO have to have this updated at the start of program (HOWEVER HTIS REQUIRES THE NEED FOR MONTHUILIST WHICH IS OUTSIDE IN SIMPLEBUDGETERUI AND I AVOIDED PASSING IT IN ORIGINALLY BUTNOW HWOHEIWOFAHEIOA)
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        statsLabel.setPreferredSize(new Dimension(0,75));
    }


    // if called from MonthSelectionListener, updates table data and stats label according to currently selected list index
    // if called from BudgetManager, updates table data and stats label according to new transaction date
    // TODO is what im doing bad lol, having the update functionality change depending on which subject is notifying it
    // TODO i could also just separate the functionalities, but there would be duplicate code
    @Override
    public void update(Observable o, Object arg) {
        String selectedDate;

        if (arg == null) {
             selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());
        } else {
            selectedDate = (String) arg;
        }
            // Update table data
            revenueTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true));
            expenseTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));

            // Update stats label
            statsLabel.setText("Net Value: $" + budgetManager.getNetValueFromDate(selectedDate));
    }
}
