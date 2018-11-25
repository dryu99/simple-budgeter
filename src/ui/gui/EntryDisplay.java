package ui.gui;

import model.BudgetManager;
import ui.gui.data_models.TransactionTableModel;

import javax.swing.*;
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
    }

    // EFFECTS: initialize table models
    private void initializeTableModels() {
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());
        revenueTableModel = new TransactionTableModel(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true)); //TODO how to invoke actionlistener events at the start of program manually
        expenseTableModel = new TransactionTableModel(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));
    }

    // EFFECTS: initializes stats label
    private void initializeStatsLabel() {
        statsLabel = new JLabel("Statistics"); // TODO have to have this updated at the start of program (HOWEVER HTIS REQUIRES THE NEED FOR MONTHUILIST WHICH IS OUTSIDE IN SIMPLEBUDGETERUI AND I AVOIDED PASSING IT IN ORIGINALLY BUTNOW HWOHEIWOFAHEIOA)
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        statsLabel.setPreferredSize(new Dimension(0,75));
    }


    // Updates table data and stats label
    @Override
    public void update(Observable o, Object arg) {
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex());

        // Update table data
        revenueTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, true));
        expenseTableModel.setTableData(budgetManager.getAllSpecifiedTransactionsFromDate(selectedDate, false));

        // Update stats label
        statsLabel.setText("" + budgetManager.getNetValueFromDate(selectedDate));
    }
}
