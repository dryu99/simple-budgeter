package model;

import java.util.List;

// TODO: static class vs Singleton (singleton prob when we need an individual class with fields, static classes don't need fields)
// CLASS: returns specific multi-lined strings related to a Budgeter
public class BudgetStringer {

    // EFFECTS: returns body part of a given transaction list, displaying the recorded transactions
    public static String bodyString(String str, List<Transaction> transactionList) {
        String body = "";
        if (!transactionList.isEmpty()) {
            for (Transaction t : transactionList) {
                body += t + "\n";
            }
        } else {
            body += "(no " + str + " for this manager)\n";
        }

        return body;
    }

    // EFFECTS: returns header part of given string (underlined by given line type)
    public static String underlinedHeaderString(String str, String lineType) {
        String header = str + "\n";
        header += lineString(str.length(), lineType) + "\n";
        return header;
    }

    // EFFECTS: returns header part of given string (with dashes on the sides)
    public static String dashedHeaderString(String str) {
        String header = "--" + str + "--";

        return header;
    }

    // EFFECTS: returns line of a given type with length of given string
    public static String lineString(int length, String lineType) {
        String line = "";

        for (int i = 0, n = length; i < n; i++) {
            line += lineType;
        }

        return line;
    }

}
