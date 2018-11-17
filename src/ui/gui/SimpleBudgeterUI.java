package ui.gui;

import model.BudgetManager;

import javax.swing.*;
import java.awt.*;

public class SimpleBudgeterUI implements Runnable {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private JFrame frame;
    // TODO: I should only put variables here that may be modified
    private JList months;
    // TODO: maybe don't make singleton
    private BudgetManager budgetManager;

    public SimpleBudgeterUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    // MODIFIES: this
    // EFFECTS: initializes graphics/components
    @Override
    public void run() {
        frame = new JFrame("Simple Budgeter");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds components for the UI
    private void createComponents(Container container) {
        String[] monthArray = {"January 2015", "February 2016"};
        months = new JList(monthArray);
        months.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        months.setLayoutOrientation(JList.VERTICAL);
        months.setVisibleRowCount(-1);

        JScrollPane monthScrollPane = new JScrollPane(months);

        container.add(monthScrollPane, BorderLayout.CENTER);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SimpleBudgeterUI(BudgetManager.getInstance()));
    }
}
