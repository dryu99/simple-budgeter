package model.date;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: okay to have these static classes? i don't want to have to instantiate these objects in every SimpleDate class just to haveaccess to their methods. Make a singleton?
public class DateFormatter {
    private static SimpleDateFormat instance = new SimpleDateFormat("dd/MM/yy");

    // EFFECTS: returns given date in simple string form dd/mm/yy
    public static String format(Date date) {
        return instance.format(date);
    }
}
