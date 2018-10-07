package model;

public class SimpleDate {
    private int month;
    private int day;
    private int year;

    public SimpleDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public SimpleDate() {
        this(0, 0, 0);
    }

    // Getters:
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getYear() { return year; }

    // TODO: implement changing dates later? (confused about whether to check for valid input here or in user prompt)
//    public void changeMonth(int newMonth) {}
//
//    public void changeDay(int newDay) {}
//
//    public void changeYear(int newYear) {}

    // EFFECTS: returns date string in form <month>/<day>/<year>
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

}
