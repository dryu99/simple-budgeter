package ui.gui;

import model.BudgetManager;
import ui.gui.data_models.EntryManagerTableModel;

import javax.swing.*;
import java.awt.*;

public class EntryManagerDisplay extends JPanel {
    private JTable entryTable; //tODO: should i put every component taht exists in this panel (i.e. scroll pane too) or just have the ones that may be modified
    private JLabel statsLabel;
    private EntryManagerTableModel tableModel;

//    private JList monthList;
    private BudgetManager budgetManager;

    public EntryManagerDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());
//
        tableModel = ui.getTableModel();
        budgetManager = ui.getBudgetManager();

        createComponents();
    }

    // MODIFIES:
    // EFFECTS: creates and adds components for the panel
    private void createComponents() { //TODO should i split up methods like i did in SimpleBudgeterUI

        // Initialize JTable
        entryTable = new JTable(tableModel);
        entryTable.setFillsViewportHeight(true);
        entryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entryTable.setGridColor(Color.BLACK);
        // TODO have to add list selection listeners to the table list selection model

        // Initialize JLabel
        statsLabel = new JLabel("Statistics");
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        statsLabel.setPreferredSize(new Dimension(0,100));

        add(new JScrollPane(entryTable));
        add(statsLabel, BorderLayout.SOUTH);
    }




}
