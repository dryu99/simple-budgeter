package ui.gui;

import model.BudgetManager;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import ui.gui.listeners.MonthSelectionListener;

import javax.swing.*;
import java.awt.*;

// TODO: not sure how much I should try to increase cohesion, because a lot of passing begins to occur (show example in github, with how I would have to pass in both MonthListDisplay AND SimpleBudgeterUI to MonthSelectionListener. alternatively i could have made monthUIList a field in SimpleBudgeterUI hmmm as essentially all the fields in this class are those that may be modified/accessed)
public class SimpleBudgeterUI implements Runnable {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    private JFrame frame;

    private MonthListDisplay monthListDisplay; // TODO feel like i have too many fields... what should normally be up here? (just the highest level components?)
    private EntryManagerDisplay entryManagerDisplay;
    private JSplitPane splitPane; // TODO ideally i would have this be a separate class and remove the monthlistdisplay and entrymanager fields but whatever
    private ButtonPanel buttonPanel;

    private BudgetManager budgetManager;

    public SimpleBudgeterUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    // Getters:
    public JFrame getFrame() { return frame; }
    public MonthListDisplay getMonthListDisplay() { return monthListDisplay; }
    public EntryManagerDisplay getEntryManagerDisplay() { return entryManagerDisplay; }
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
        centerOnScreen();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds components for the UI
    private void createComponents() {
        // initialize main components
        initializeSplitPane();
        buttonPanel = new ButtonPanel(this);

        initializeListeners();

        // add main components
        frame.add(splitPane); // TODO: better to add here or in the init methods?
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes UI split pane component
    private void initializeSplitPane() {
        // initialize month list and entry manager displays
        monthListDisplay = new MonthListDisplay(this);
        entryManagerDisplay = new EntryManagerDisplay(this);

        // wrap month list display in scroll pane //TODO maybe dont have to do this if I make a scroll pane inside month list display
        JScrollPane monthScrollPane = new JScrollPane(monthListDisplay);
        monthScrollPane.setMinimumSize(new Dimension(100, HEIGHT));
//        entryScrollPane.setMinimumSize(new Dimension(WIDTH - (WIDTH / 5), HEIGHT)); // TODO: what min size do i want fo rirhgt side

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, monthScrollPane, entryManagerDisplay);
    }

    // MODIFIES: this //TODO have to initialize them outside classes and after creating ALL components because listeners may need components from multiple places and all need to not be null in order to work?
    // EFFECTS: initializes listeners
    private void initializeListeners() {
        monthListDisplay.getMonthUIList().addListSelectionListener(new MonthSelectionListener(this));
    }

    // MODIFIES: this
    // EFFECTS: centers the frame in the middle of the screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().width;

        frame.setLocation((width - frame.getWidth()) / 2, 100); //TODO: don't know how to do the y part
    }

    public static void main(String[] args) {
        BudgetManager budgetManager = new BudgetManager();

//        Entry entry1 = new Entry(new SimpleDate(2018, 2, 20));
//        Entry entry2 = new Entry(new SimpleDate(2018, 3, 20));
//        Entry entry3 = new Entry(new SimpleDate(2018, 4, 20));
//        Entry entry4 = new Entry(new SimpleDate(2018,2,25));

        SimpleDate date1 = new SimpleDate(2018, 2, 20);
        SimpleDate date1a = new SimpleDate(2018, 2, 25);

        SimpleDate date2 = new SimpleDate(2018, 3, 20);
        SimpleDate date2a = new SimpleDate(2018, 3, 15);

        SimpleDate date3 = new SimpleDate(2018, 4, 20);
        SimpleDate date3a = new SimpleDate(2018, 4, 16);

        SimpleDate date4 = new SimpleDate(2018, 12, 20);
        SimpleDate date4a = new SimpleDate(2018, 12, 17);

        Transaction revenue1 = new Transaction(10d, "TA Payroll", RevGenre.PAYCHEQUE, date1);
        Transaction expense1 = new Transaction(-20.67, "McDonalds", ExpGenre.FOOD, date1a);
        Transaction expense2 = new Transaction(-50d, "Boots", ExpGenre.SHOPPING, date1a);

        Transaction revenue2 = new Transaction(20.56, "Yon-Jun IOU", RevGenre.IOU, date2);
        Transaction revenue3 = new Transaction(60d, "Music Gig", RevGenre.PAYCHEQUE, date2a);

        Transaction expense3 = new Transaction(-30d, "Computer", ExpGenre.SHOPPING,date3);
        Transaction expense4 = new Transaction(-46.75, "Dinner Date w/ Mom", ExpGenre.FOOD,date3a);

        Transaction expense5 = new Transaction(-25d, "Band equipment", ExpGenre.SHOPPING,date4);
        Transaction expense6 = new Transaction(-46, "Booze", ExpGenre.DRINK, date4a);
//
//        entry1.addTransaction(revenue1);
//        entry1.addExpense(expense1);
//        entry1.addExpense(expense2);
//
//        entry2.addTransaction(revenue2);
//        entry2.addTransaction(revenue3);
//
//        entry3.addExpense(expense3);
//        entry3.addExpense(expense4);
//
//        entry4.addExpense(expense5);
//        entry4.addExpense(expense6);

        budgetManager.addTransaction(revenue1);
        budgetManager.addTransaction(expense1);
        budgetManager.addTransaction(expense2);
        budgetManager.addTransaction(revenue2);
        budgetManager.addTransaction(revenue3);
        budgetManager.addTransaction(expense3);
        budgetManager.addTransaction(expense4);
        budgetManager.addTransaction(expense5);
        budgetManager.addTransaction(expense6);

        SwingUtilities.invokeLater(new SimpleBudgeterUI(budgetManager));
    }
}
