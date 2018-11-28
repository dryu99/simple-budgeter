package ui.gui.renderers;

import model.date.SimpleDate;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DateCellRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object date,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        JLabel component = (JLabel) super.getTableCellRendererComponent(table, date, isSelected, hasFocus, row, column);

        String simpleFormattedDate = ((SimpleDate) date).simpleFormat();

        String description =


    }
}
