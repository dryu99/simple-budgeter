package ui.gui;

import model.BudgetManager;
import model.Entry;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import ui.gui.data_models.TransactionTableModel;
import ui.gui.listeners.MonthSelectionListener;

import javax.swing.*;
import java.awt.*;
// TODO: not sure how much I should try to increase cohesion, because a lot of passing begins to occur (show example in github, with how I would have to pass in both MonthListDisplay AND SimpleBudgeterUI to MonthSelectionListener. alternatively i could have made monthUIList a field in SimpleBudgeterUI hmmm as essentially all the fields in this class are those that may be modified/accessed)
public class SimpleBudgeterUI implements Runnable {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    private JFrame frame;

    private JPanel monthListDisplay;
    private JList monthUIList;
    private EntryManagerDisplay entryManagerDisplay; // TODO: this will be a TABLE
    private TransactionTableModel revenueTableModel;
    private TransactionTableModel expenseTableModel;
    private JSplitPane splitPane;
    private ButtonPanel buttonPanel;

    private BudgetManager budgetManager;

    public SimpleBudgeterUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    // Getters:
    public JFrame getFrame() { return frame; }
    public JPanel getMonthListDisplay() { return monthListDisplay; }
    public JList getMonthUIList() { return monthUIList; }
    public EntryManagerDisplay getEntryManagerDisplay() { return entryManagerDisplay; }
    public TransactionTableModel getRevenueTableModel() { return revenueTableModel; }
    public TransactionTableModel getExpenseTableModel() { return expenseTableModel; }
    public ButtonPanel getButtonPanel() { return buttonPanel; }
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
        initializeSplitPane();
        initializeButtonPanel();
        initializeListeners();

        frame.add(splitPane);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    // MODIFIES: this
    // EFFECTS: initializes UI split pane component
    private void initializeSplitPane() {
        initializeMonthListDisplay();
        initializeEntryManagerDisplay();

        JScrollPane monthScrollPane = new JScrollPane(monthListDisplay);
//        JScrollPane entryScrollPane = new JScrollPane(entryManagerDisplay);

        monthScrollPane.setMinimumSize(new Dimension(100, HEIGHT));
//        entryScrollPane.setMinimumSize(new Dimension(WIDTH - (WIDTH / 5), HEIGHT)); // TODO: what min size do i want fo rirhgt side

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, monthScrollPane, entryManagerDisplay);
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

        monthListDisplay.add(monthLabel, BorderLayout.PAGE_START);
        monthListDisplay.add(monthUIList);
    }

    // MODIFIES: this
    // EFFECTS: initializes entry manager display
    private void initializeEntryManagerDisplay() {
        String selectedDate = budgetManager.getMonths().get(monthUIList.getSelectedIndex()); // TODO: this is the method call that's preventing me from smoothly creating a monthDisplay class, because it needs access to monthUIlist
        revenueTableModel = new TransactionTableModel(budgetManager.getAllTransactionsFromDate(selectedDate)); //TODO: perhaps I can invoke a list selection event to set up the data? instead of setting it up this way
        expenseTableModel = new TransactionTableModel(budgetManager.getAllTransactionsFromDate(selectedDate));

        entryManagerDisplay = new EntryManagerDisplay(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes button pannel
    private void initializeButtonPanel() {
        buttonPanel = new ButtonPanel(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes listeners
    private void initializeListeners() {
        monthUIList.addListSelectionListener(new MonthSelectionListener(this));
    }

    // MODIFIES: this
    // EFFECTS: centers the frame in the middle of the screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().width;

        frame.setLocation((width - frame.getWidth()) / 2, 100); //TODO: dont know how to do the y part
    }

    public static void main(String[] args) {
        BudgetManager budgetManager = BudgetManager.getInstance();

        Entry entry1 = new Entry(new SimpleDate(2018, 2, 20));
        Entry entry2 = new Entry(new SimpleDate(2018, 3, 20));
        Entry entry3 = new Entry(new SimpleDate(2018, 4, 20));
        Entry entry4 = new Entry(new SimpleDate(2018,2,25));

        Transaction revenue1 = new Transaction(10d, "TA Payroll", RevGenre.PAYCHEQUE);
        Transaction expense1 = new Transaction(-20.67, "McDonalds", ExpGenre.FOOD);
        Transaction expense2 = new Transaction(-50d, "Boots", ExpGenre.SHOPPING);

        Transaction revenue2 = new Transaction(20.56, "Yon-Jun IOU", RevGenre.IOU);
        Transaction revenue3 = new Transaction(60d, "Music Gig", RevGenre.PAYCHEQUE);

        Transaction expense3 = new Transaction(-30d, "Computer", ExpGenre.SHOPPING);
        Transaction expense4 = new Transaction(-46.75, "Dinner Date w/ Mom", ExpGenre.FOOD);

        Transaction expense5 = new Transaction(-25d, "Band equipment", ExpGenre.SHOPPING);
        Transaction expense6 = new Transaction(-46, "Booze", ExpGenre.DRINK);

        entry1.addRevenue(revenue1);
        entry1.addExpense(expense1);
        entry1.addExpense(expense2);

        entry2.addRevenue(revenue2);
        entry2.addRevenue(revenue3);

        entry3.addExpense(expense3);
        entry3.addExpense(expense4);

        entry4.addExpense(expense5);
        entry4.addExpense(expense6);

        budgetManager.addEntry(entry1);
        budgetManager.addEntry(entry2);
        budgetManager.addEntry(entry3);
        budgetManager.addEntry(entry4);

        SwingUtilities.invokeLater(new SimpleBudgeterUI(budgetManager));
    }
}
