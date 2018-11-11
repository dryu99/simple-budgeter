package model.date;

import java.text.DateFormatSymbols;

public class SingleDateFormatSymbols {
    private static DateFormatSymbols instance = new DateFormatSymbols();

    // EFFECTS: returns given month in String form
    public static String getMonth(int month) {
        return instance.getMonths()[month];
    }
}
