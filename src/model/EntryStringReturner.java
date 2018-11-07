package model;

import java.util.List;

public class EntryStringReturner {

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
    public static String headerString(String str, String lineType) {
        String header = str + "\n";
        header += lineString(str, lineType) + "\n";
        return header;
    }

    // EFFECTS: returns line of a given type with length of given string
    private static String lineString(String str, String lineType) {
        String line = "";

        for (int i = 0, n = str.length(); i < n; i++) {
            line += lineType;
        }

        return line;
    }
}
