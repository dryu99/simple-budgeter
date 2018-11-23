package ui.gui;

import javax.swing.*;
import java.awt.*;

public class AddButtonDialog extends JDialog {
     private final int WIDTH = 400;
     private final int HEIGHT = 500;

    public AddButtonDialog(JFrame parent) {
        super(parent);
        setTitle("Add a Transaction");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents();

        pack();
        setVisible(true);
    }

    // EFFECTS: creates and adds components to this dialog
    private void createComponents() {

    }

}
