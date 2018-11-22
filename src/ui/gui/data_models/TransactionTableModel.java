package ui.gui.data_models;

import model.Transaction;
import model.date.SimpleDate;
import model.enums.Genre;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TransactionTableModel extends AbstractTableModel { //tODO make this a singleton?
    private List<Transaction> data;

    private String[] columnNames = {"Date", "Amount", "Genre"};
    private Class[] columnClasses = {SimpleDate.class, String.class, Genre.class}; // TODO: after making customized renderer, turn string int double

    public TransactionTableModel(List<Transaction> transactionList) {
        this.data = transactionList;
    }

    public TransactionTableModel() {
        data = null;
    }

    // EFFECTS: returns number of rows in this table
    @Override
    public int getRowCount() {
        return data.size();
    }

    // EFFECTS: returns number of columns in this table
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // EFFECTS: returns name of column at given col index
    @Override
    public String getColumnName(int colIndex) {
        return columnNames[colIndex];
    }

    // EFFECTS: returns class of column at given col index
    @Override
    public Class<?> getColumnClass(int colIndex) {
        return columnClasses[colIndex];
    }

    // EFFECTS: returns cell value at given row and column
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction row = data.get(rowIndex);

        if (columnIndex == 0) {
            return row.getDate();
        } else if (columnIndex == 1) {
            return row.getFormattedValue();
        } else if (columnIndex == 2) {
            return row.getGenre();
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets table with new data
    public void setTableData(List<Transaction> newData) {
        data = newData;
        fireTableDataChanged();
    }
}
