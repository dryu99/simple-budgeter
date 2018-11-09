package model;

import model.exceptions.InvalidDayException;
import model.exceptions.InvalidMonthException;
import model.exceptions.InvalidYearException;

public class SimpleDate {
    private int month;
    private int day;
    private int year;

    // TODO: have exceptions here?
    // Constructors:
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
    // Setters:

    // MODIFIES: this
    // EFFECTS: if given month is not [1, 12], throw InvalidMonthException
    //          ow, set month to given month
    public void setMonth(int newMonth) throws InvalidMonthException {
        if (newMonth < 1 || newMonth > 12) {
            throw new InvalidMonthException(newMonth);
        }
        this.month = newMonth;
    }

    // MODIFIES: this
    // EFFECTS: if given day is not [1, 31], throw InvalidDayException
    //          ow, set day to given day
    public void setDay(int newDay) throws InvalidDayException {
        if (newDay < 1 || newDay > 31) {
            throw new InvalidDayException(newDay);
        }
        this.day = newDay;
    }

    // MODIFIES: this
    // EFFECTS: if given year is < 0, throw InvalidYearException
    //          ow, set year to given year
    public void setYear(int newYear) throws InvalidYearException {
        if (newYear < 0) {
            throw new InvalidYearException(newYear);
        }
        this.year = newYear;
    }

    // EFFECTS: returns date string in form <month>/<day>/<year>
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    // EFFECTS: returns true if date has same m/d/y as compared date
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() == o.getClass()) {
            return false;
        }

        SimpleDate compared = (SimpleDate) o;

        return month == compared.month && day == compared.day &&
                year == compared.year;
    }

    // TODO: possible to have same hashCode for certain days? how to prevent (i.e. 2/20/1999 = 2/19/2000)
    // EFFECTS: returns unique id based on the date's id and date
    @Override
    public int hashCode() {
        int result = (month * 7) + (day * 17) + (year * 31);
        return result;
    }

}
