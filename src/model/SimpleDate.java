package model;

import model.date.DateFormatter;
import model.date.SingleDateFormatSymbols;
import model.exceptions.InvalidDateException;
import model.exceptions.InvalidDayException;
import model.exceptions.InvalidMonthException;
import model.exceptions.InvalidYearException;

import java.util.Calendar;

public class SimpleDate {
    private Calendar calendar = Calendar.getInstance();
    private String simpleFormat;

    // TODO: have exceptions here?
    // Constructors:

    // EFFECTS: creates a date object with today's date info
    public SimpleDate() {
        createSimpleFormat();
    }

    // EFFECTS: creates a date object with the given date info
    public SimpleDate(int year, int month, int day) {
        calendar.set(year, month, day);
        createSimpleFormat();
    }

    // Getters:
    public int get(int calField) { return calendar.get(calField); }
    public String getSimpleFormat() { return simpleFormat; }

    public int getYear() { return calendar.get(Calendar.YEAR); }
    public int getMonth() { return calendar.get(Calendar.MONTH); }
    public int getDay() { return calendar.get(Calendar.DATE); }


    private void createSimpleFormat() {
        String monthStr = SingleDateFormatSymbols.getMonth(calendar.get(Calendar.MONTH));
        simpleFormat = monthStr + " " + calendar.get((Calendar.YEAR));
    }


    // TODO: implement changing dates later? (confused about whether to check for valid input here or in user prompt)
    // Setters:

    // REQUIRES: valid calField and value
    // MODIFIES: this //TODO: do i write modifies this here, or where the actual modification is occuring
    // EFFECTS: throws InvalidDateException if invalid value is given
    //          ow, sets given value to the given calendar field
    public void set(int calField, int value) throws InvalidDateException {
        if (calField == Calendar.YEAR) {
            setYear(value);
        } else if (calField == Calendar.MONTH) {
            setMonth(value);
        } else if (calField == Calendar.DATE) {
            setDay(value);
        }
    }

    // MODIFIES: this
    // EFFECTS: if given month is not [1, 12], throw InvalidMonthException
    //          ow, set month to given month
    private void setMonth(int newMonth) throws InvalidMonthException {
        if (newMonth < 1 || newMonth > 12) {
            throw new InvalidMonthException(newMonth);
        }
        calendar.set(Calendar.MONTH, newMonth - 1);
    }

    // MODIFIES: this
    // EFFECTS: if given day is not [1, 31], throw InvalidDayException
    //          ow, set day to given day
    private void setDay(int newDay) throws InvalidDayException {
        if (newDay < 1 || newDay > 31) {
            throw new InvalidDayException(newDay);
        }
        calendar.set(Calendar.DATE, newDay);
    }

    // MODIFIES: this
    // EFFECTS: if given year is < 0, throw InvalidYearException
    //          ow, set year to given year
    private void setYear(int newYear) throws InvalidYearException {
        if (newYear < 0) {
            throw new InvalidYearException(newYear);
        }
        calendar.set(Calendar.YEAR, newYear);
    }

    // EFFECTS: returns date string in form <month>/<day>/<year>
    @Override
    public String toString() {
        return DateFormatter.format(calendar.getTime());
    }

//    // EFFECTS: returns true if date has same m/d/y as compared date
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) {
//            return false;
//        }
//
//        if (this.getClass() == o.getClass()) {
//            return false;
//        }
//
//        SimpleDate compared = (SimpleDate) o;
//
//        return month == compared.month && day == compared.day &&
//                year == compared.year;
//    }
//
//    // TODO: possible to have same hashCode for certain days? how to prevent (i.e. 2/20/1999 = 2/19/2000)
//    // EFFECTS: returns unique id based on the date's id and date
//    @Override
//    public int hashCode() {
//        int result = (month * 7) + (day * 17) + (year * 31);
//        return result;
//    }




}
