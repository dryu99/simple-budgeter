package ui;

import java.util.Scanner;

// SINGLETON CLASS: only one scanner for budgeting app.
public class SingleScanner {
    private static Scanner instance = new Scanner(System.in);

    // EFFECTS: returns singular Scanner instance
    public static Scanner getInstance() {
        return instance;
    }

}
