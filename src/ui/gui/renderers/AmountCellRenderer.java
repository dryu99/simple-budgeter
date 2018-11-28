package ui.gui.renderers;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;

// Cell renderer for the amount column in TransactionTableModels
public class AmountCellRenderer extends DefaultTableCellRenderer {
    private final DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void setValue(Object value) {
        Double amount = (Double) value;
        amount = amount < 0 ? amount * -1 : amount;

        setText("$" + df.format(amount));
    }
}
