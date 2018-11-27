package model.date;

import java.util.Calendar;

public class DateArray {
    private static Calendar calendar = Calendar.getInstance();

    // EFFECTS: returns array of the days in a standard month //tODO should make this adjustable according to the month of interest (feb = 28 days)
    public static String[] getDays() {
        int maxDays = calendar.getMaximum(Calendar.DATE);
        String[] days = new String[maxDays];

        for (int i = 0; i < maxDays; i++) {
            days[i] = "" + (i+1);
        }
        return days;
    }

    // EFFECTS: returns array of the months (num value) in an year
    public static String[] getMonths() {
        int maxMonths = calendar.getMaximum(Calendar.MONTH) + 1;
        String[] months = new String[maxMonths];

        for (int i = 0; i < maxMonths; i++) {
            months[i] = "" + (i+1);
        }
        return months;
    }

    // EFFECTS: returns array of last 30 years //TODO should update every year
    public static String[] getYears() {
        String[] years = new String[30];

        for (int i = 0; i < 30; i++) {
            years[i] = "" + (i + 1990);
        }

        return years;
    }

}
