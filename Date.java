/* Date.java */

public class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  public int getMonth() { return month; }
  public int getDay() { return day; }
  public int getYear() { return year; }

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    if (isValidDate(month, day, year)) {
      this.month = month;
      this.day = day;
      this.year = year;
    } else { this.month = this.day = this.year = 0; }
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
    String[] dayStr = s.split("/");
    int lMonth = Integer.parseInt(dayStr[0]);
    int lDay = Integer.parseInt(dayStr[1]);
    int lYear = Integer.parseInt(dayStr[2]);
    if (isValidDate(lMonth, lDay, lYear)) {
      month = lMonth;
      day = lDay;
      year = lYear;
    } else { this.month = this.day = this.year = 0; }
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */

  public static boolean isLeapYear(int year) {
    if (year % 100 == 0) {
      if (year % 400 == 0) { return true; }
      return false;
    }
    else if (year % 4 == 0) { return true; }
    return false;
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return 30;
    } else if (month == 2) {
      if (isLeapYear(year)) { return 29; }
      else { return 28; }
    }
    return 31;
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if (day > 0 && day <= daysInMonth(month, year)) { return true; }
    return false;
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    String date = month + "/" + day + "/" + year;
    return date;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    if (year < d.getYear()) {
      return true;
    } else if ( year == d.getYear()) {
      if (month < d.getMonth()) {
        return true;
      } else if (month == d.getMonth()) {
        if (day < d.getDay()) {
          return true;
        }
      }
    }
    return false;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if (!isBefore(d)) {
      if (!(year == d.getYear() && month == d.getMonth() && day == d.getDay())) {
        return true;
      }
    }
    return false;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */ 
  public int dayInYear() {
    int days = day;
    if (month >= 2) {
      for (int i = 2; i <= month; i++) { 
        days += daysInMonth(i-1, year);
      }
    }
    return days;
  }
  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    int diff;
    if (year == d.getYear()) {
      diff = dayInYear() - d.dayInYear();
    } else {
      diff = dayInYear();
      if (isLeapYear(d.getYear())) {
        diff += (366 - d.dayInYear());
      } else {
        diff += (365 - d.dayInYear());
      }
      for (int i = d.getYear()+1; i < year; i++) {
        if (isLeapYear(i)) {
          diff += 366;
        } else {
          diff += 365;
        }
      }
    }
    return diff;
  }

  // public static void main(String[] argv) {
  //   System.out.println("\nTesting constructors.");
  //   Date d1 = new Date(1, -1, 1);
  //   System.out.println("Date should be 0/0/0: " + d1);
  //   d1 = new Date("1/-1/0");
  //   System.out.println("Date should be 0/0/0: " + d1);
  //   d1 = new Date("2/4/2");
  //   System.out.println("Date should be 2/4/2: " + d1);
  //   d1 = new Date("2/29/2000");
  //   System.out.println("Date should be 2/29/2000: " + d1);
  //   d1 = new Date("2/29/1904");
  //   System.out.println("Date should be 2/29/1904: " + d1);

  //   d1 = new Date(12, 31, 1975);
  //   System.out.println("Date should be 12/31/1975: " + d1);
  //   Date d2 = new Date(1, 1, 1976); //"1/1/1976"
  //   System.out.println("Date should be 1/1/1976: " + d2);
  //   Date d3 = new Date("1/2/1976");
  //   System.out.println("Date should be 1/2/1976: " + d3);

  //   Date d4 = new Date("2/27/1977");
  //   Date d5 = new Date("8/31/2110");

  //   Date d6 = new Date("1/1/1");
  //   Date d7 = new Date("8/11/2020");

  //   System.out.println("\nTesting before and after.");
  //   System.out.println(d2 + " after " + d1 + " should be true: " + 
  //                      d2.isAfter(d1));
  //   System.out.println(d3 + " after " + d2 + " should be true: " + 
  //                      d3.isAfter(d2));
  //   System.out.println(d1 + " after " + d1 + " should be false: " + 
  //                      d1.isAfter(d1));
  //   System.out.println(d1 + " after " + d2 + " should be false: " + 
  //                      d1.isAfter(d2));
  //   System.out.println(d2 + " after " + d3 + " should be false: " + 
  //                      d2.isAfter(d3));

  //   System.out.println(d1 + " before " + d2 + " should be true: " + 
  //                      d1.isBefore(d2));
  //   System.out.println(d2 + " before " + d3 + " should be true: " + 
  //                      d2.isBefore(d3));
  //   System.out.println(d1 + " before " + d1 + " should be false: " + 
  //                      d1.isBefore(d1));
  //   System.out.println(d2 + " before " + d1 + " should be false: " + 
  //                      d2.isBefore(d1));
  //   System.out.println(d3 + " before " + d2 + " should be false: " + 
  //                      d3.isBefore(d2));

  //   System.out.println("\nTesting difference.");
  //   System.out.println(d1 + " - " + d1  + " should be 0: " + 
  //                      d1.difference(d1));
  //   System.out.println(d2 + " - " + d1  + " should be 1: " + 
  //                      d2.difference(d1));
  //   System.out.println(d3 + " - " + d1  + " should be 2: " + 
  //                      d3.difference(d1));
  //   System.out.println(d5 + " - " + d4  + " should be 48762: " + 
  //                      d5.difference(d4));
  //   System.out.println(d7 + " - " + d6  + " should be 737647: " + 
  //                      d7.difference(d6)); 
  // }
}
