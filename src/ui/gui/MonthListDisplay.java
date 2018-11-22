package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MonthListDisplay extends JPanel {
    private JLabel header;
    private JList monthUIList;
    private DefaultListModel monthsModel;

    private SimpleBudgeterUI mainUI;

    public MonthListDisplay(SimpleBudgeterUI ui) {
        super(new BorderLayout());
        mainUI = ui;

        createComponents();
    }

    private void createComponents() {
        header = new JLabel("Months");
        header.setHorizontalAlignment(JLabel.CENTER);

        monthsModel = new DefaultListModel();
        for (String s : mainUI.getBudgetManager()) {
            monthsModel.addElement(s);
        }

        monthUIList = new JList(monthsModel);
        monthUIList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monthUIList.setSelectedIndex(0);
        monthUIList.setLayoutOrientation(JList.VERTICAL);
        monthUIList.setVisibleRowCount(-1);

        add(header, BorderLayout.NORTH);
        add(monthUIList);
    }

}
