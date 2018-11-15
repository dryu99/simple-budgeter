package model.date;

import model.exceptions.InvalidDateException;
import model.exceptions.InvalidDayException;
import model.exceptions.InvalidMonthException;
import model.exceptions.InvalidYearException;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class SimpleDate implements Comparable<SimpleDate> {
    private Calendar calendar = Calendar.getInstance();
    private String simpleFormat;

    // TODO: have exceptions here?
    // Constructors:

    // EFFECTS: creates a date object with today's date info
    public SimpleDate() {
        setSimpleFormat();
    }

    // EFFECTS: creates a date object with the given date info
    public SimpleDate(int year, int month, int day) {
        calendar.set(year, month - 1, day);
        setSimpleFormat();
    }

    // Getters:
    public int get(int calField) {
        if (calField == Calendar.MONTH) {
            return calendar.get(calField) + 1;
        }

        return calendar.get(calField);
    }
    public String simpleFormat() { return simpleFormat; }

    // Setters:

    // REQUIRES: valid calField and value
    // MODIFIES: this //TODO: do i write modifies this here, or where the actual modification is occuring (in set methods)
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

    // EFFECTS: returns date string in form <month>/<day>/<year>
    @Override
    public String toString() {
        return DateFormatter.format(calendar.getTime());
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
    // NOTES: keep in mind that when setting an invalid date that is within the range [1,31]
    //        the method will not throw anything, but will instead add to the day instead (e.g. 31 for Feb)
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

    // EFFECTS: sets simpleFormat field according to current calendar date in string form "<month> <year>"
    private void setSimpleFormat() {
        String monthStr = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
        simpleFormat = monthStr + " " + calendar.get((Calendar.YEAR));
    }



    // EFFECTS: returns true if date has same y/m/d and simple format as compared date
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        SimpleDate compared = (SimpleDate) o;

        // TODO: i call these get methods so much, should i just make a field for them in this class?
        // TODO: but i dont think i can because they arent references and wont update whenever i set new values for the calendar

        if (calendar != null ? get(Calendar.YEAR) != compared.get(Calendar.YEAR) ||
                get(Calendar.MONTH) != compared.get(Calendar.MONTH) ||
                get(Calendar.DATE) != compared.get(Calendar.DATE) : compared.calendar != null) {
            return false;
        }

        if (simpleFormat != null ? !simpleFormat.equals(compared.simpleFormat) : compared.simpleFormat != null) {
            return false;
        }

        return true;
    }

    // TODO: possible to have same hashCode for certain days? how to prevent (i.e. 2/20/1999 = 2/19/2000)
    // TODO: should I just have the hashcode be calendar.hashcode()
    // EFFECTS: returns unique id based on the date's y/m/d and simple format
    @Override
    public int hashCode() {
        int result = (calendar.get(Calendar.YEAR) * 7) + (calendar.get(Calendar.MONTH) * 17) + (calendar.get(Calendar.DATE) * 31);
        result += simpleFormat.hashCode();

        return result;
    }

    // EFFECTS: returns positive number if this date is older than compared
    @Override
    public int compareTo(SimpleDate compared) {
        return calendar.compareTo(compared.calendar);
    }




}
