package ui.gui;

import model.BudgetManager;
import model.Entry;
import model.date.SimpleDate;
import ui.gui.listeners.MonthSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//TODO: have to make JPanel for entry manager, entry, transaction manager?
public class SimpleBudgeterUI implements Runnable {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private JFrame frame;

    private JList monthUIList;
    private DefaultListModel monthsModel;
    private JLabel entryManagerDisplay;
    private JSplitPane splitPane;
    private BudgetManager budgetManager;  // TODO: maybe don't make singleton

    public SimpleBudgeterUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    // Getters:
    public JList getMonthUIList() { return monthUIList; }
    public DefaultListModel getMonthsModel() { return monthsModel; }
    public JLabel getEntryManagerDisplay() { return entryManagerDisplay; }
    public BudgetManager getBudgetManager() { return budgetManager; }

    // MODIFIES: this
    // EFFECTS: initializes graphics/components
    @Override
    public void run() {
        frame = new JFrame("Simple Budgeter");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();

        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds components for the UI
    private void createComponents() {
        // TODO : better to make class fields, or declare variables here and pass them through the initializing methods
        initializeMonthUIList();
        initializeEntryLabel();
        initializeSplitPane();

        initializeListeners();

        frame.add(splitPane);
    }

    // MODIFIES: this
    // EFFECTS: initializes UI month list and list model components
    private void initializeMonthUIList() {
        monthsModel = new DefaultListModel();
        List<String> monthsList = budgetManager.getMonths();

        for (String s : monthsList) {
            monthsModel.addElement(s);
        }

        monthUIList = new JList(monthsModel);
        monthUIList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monthUIList.setSelectedIndex(0);
        monthUIList.setLayoutOrientation(JList.VERTICAL);
        monthUIList.setVisibleRowCount(-1);
    }

    private void initializeEntryLabel() {
        entryManagerDisplay = new JLabel(budgetManager.getMonths().get(monthUIList.getSelectedIndex()));
    }

    // MODIFIES: this
    // EFFECTS: initializes UI split pane component
    private void initializeSplitPane() {
        JScrollPane monthScrollPane = new JScrollPane(monthUIList);
        JScrollPane entryScrollPane = new JScrollPane(entryManagerDisplay);

        monthScrollPane.setMinimumSize(new Dimension(WIDTH / 5, HEIGHT));
//        entryScrollPane.setMinimumSize(new Dimension(WIDTH - (WIDTH / 5), HEIGHT)); // TODO: what min size do i want fo rirhgt side

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, monthScrollPane, entryScrollPane);
    }

    private void initializeListeners() {
        monthUIList.addListSelectionListener(new MonthSelectionListener(this));
    }


    public static void main(String[] args) {
        BudgetManager budgetManager = BudgetManager.getInstance();
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 2, 20)));
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 3, 20)));
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 4, 20)));

        SwingUtilities.invokeLater(new SimpleBudgeterUI(budgetManager));
    }
}
