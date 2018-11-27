package ui.gui;

import model.BudgetManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MonthListDisplay extends JPanel implements Observer {
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
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        header.setHorizontalAlignment(JLabel.CENTER);
    }

    // EFFECTS: initializes month ui list
    private void initializeMonthList() {
        monthModel = new DefaultListModel();
        for (String monthYearDate : budgetManager) {
            monthModel.addElement(monthYearDate);
        }

        monthUIList = new JList(monthModel);
        monthUIList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monthUIList.setSelectedIndex(0);
        monthUIList.setLayoutOrientation(JList.VERTICAL);
        monthUIList.setVisibleRowCount(-1);
    }

    // EFFECTS: If new date was added, update monthUIList to include the new date, and sets the selected index to be at the date
    //          If new date wasn't added, just set the new selected index to be at the date of the added transaction
    @Override
    public void update(Observable o, Object arg) {
        String newTransactionDate = (String) arg;

        if (!monthModel.contains(newTransactionDate)) {
            int newIndex = budgetManager.getIndexOfDate(newTransactionDate);
            monthModel.add(newIndex, newTransactionDate);
        }

        monthUIList.setSelectedIndex(budgetManager.getIndexOfDate(newTransactionDate));
    }
}