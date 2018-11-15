package model.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static SimpleDateFormat instance = new SimpleDateFormat("dd/MM/yy");

    // EFFECTS: returns given date in simple string form dd/mm/yy
    public static String format(Date date) {
        return instance.format(date);
    }
}
