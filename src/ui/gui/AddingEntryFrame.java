package ui.gui;

import javax.swing.*;
import java.awt.*;

public class AddingEntryFrame extends JInternalFrame {
    // private final int WIDTH = ui.getWidth / 5
    // // private final int HEIGHT = ui.getWidth / 5

    public AddingEntryFrame() {
        super("Adding Entry",
                false,
                true,
                false,
                true);

        createComponents();
        setSize(new Dimension(200,100));
        pack();
//        setLocation(500, 500);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds components for this frame
    private void createComponents() {

    }
}
