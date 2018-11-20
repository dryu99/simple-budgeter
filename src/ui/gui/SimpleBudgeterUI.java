package ui.gui;

import model.BudgetManager;
import model.Entry;
import model.date.SimpleDate;
import ui.gui.listeners.MonthSelectionListener;

import javax.swing.*;
import java.awt.*;
// TODO: not sure how much I should try to increase cohesion, because a lot of passing begins to occur (show example in github, with how I would have to pass in both MonthListDisplay AND SimpleBudgeterUI to MonthSelectionListener. alternatively i could have made monthUIList a field in SimpleBudgeterUI hmmm as essentially all the fields in this class are those that may be modified/accessed)
public class SimpleBudgeterUI implements Runnable {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 600;

    private JFrame frame;

    private JList monthUIList;
    private JPanel monthListDisplay;
    private JPanel entryManagerDisplay; // TODO: this will be a TABLE
    private JSplitPane splitPane;
    private ButtonPanel buttonPanel;

    private BudgetManager budgetManager;

    public SimpleBudgeterUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    // Getters:
    public JFrame getFrame() { return frame; }
    public JList getMonthUIList() { return monthUIList; }
    public JPanel getMonthListDisplay() { return monthListDisplay; }
    public JPanel getEntryManagerDisplay() { return entryManagerDisplay; }
    public BudgetManager getBudgetManager() { return budgetManager; }

    // MODIFIES: this
    // EFFECTS: initializes graphics/components
    @Override
    public void run() {
        frame = new JFrame("Simple Budgeter");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        desktopPane = new JDesktopPane();
//        frame.setContentPane(desktopPane);

        createComponents();

        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds components for the UI
    private void createComponents() {
        initializeSplitPane();
        initializeButtonPanel();

        frame.add(splitPane);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes UI split pane component
    private void initializeSplitPane() {
        initializeMonthListDisplay();
        initializeEntryManagerDisplay();

        JScrollPane monthScrollPane = new JScrollPane(monthListDisplay);
        JScrollPane entryScrollPane = new JScrollPane(entryManagerDisplay);

        monthScrollPane.setMinimumSize(new Dimension(100, HEIGHT));
//        entryScrollPane.setMinimumSize(new Dimension(WIDTH - (WIDTH / 5), HEIGHT)); // TODO: what min size do i want fo rirhgt side

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, monthScrollPane, entryScrollPane);
    }

    // TODO: see i wish i could just make a month list display class for this panel
    // MODIFIES: this
    // EFFECTS: initializes month list display
    private void initializeMonthListDisplay() {
        monthListDisplay = new JPanel(new BorderLayout());

        JLabel monthLabel = new JLabel("Months"); // TODO: make a nice border for this
        monthLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultListModel monthsModel = new DefaultListModel();
        for (String s : budgetManager) {
            monthsModel.addElement(s);
        }

        monthUIList = new JList(monthsModel);
        monthUIList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monthUIList.setSelectedIndex(0);
        monthUIList.setLayoutOrientation(JList.VERTICAL);
        monthUIList.setVisibleRowCount(-1);
        monthUIList.addListSelectionListener(new MonthSelectionListener(this));

        monthListDisplay.add(monthLabel, BorderLayout.PAGE_START);
        monthListDisplay.add(monthUIList);
    }

    // TODO: this will be a TABLE
    // MODIFIES: this
    // EFFECTS: initializes entry manager display
    private void initializeEntryManagerDisplay() {
        entryManagerDisplay = new EntryManagerDisplay(this);
    }

    private void initializeButtonPanel() {
        buttonPanel = new ButtonPanel(this);
    }

    public static void main(String[] args) {
        BudgetManager budgetManager = BudgetManager.getInstance();
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 2, 20)));
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 3, 20)));
        budgetManager.addEntry(new Entry(new SimpleDate(2018, 4, 20)));

        SwingUtilities.invokeLater(new SimpleBudgeterUI(budgetManager));
    }
}
