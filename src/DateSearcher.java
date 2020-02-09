import java.util.ArrayList;
import java.util.*;

//A class for parsing and comparing dates
public class DateSearcher {
	//constructor: takes no parameters
	DateSearcher() {
		}
	
	//Reverser: reverses the order of the organized list
	public static ArrayList<String> reverser(ArrayList<String> dates) {
		ArrayList<String> newDay = new ArrayList<String>();

		for (int i = dates.size() - 1; i >= 0; i--) {
			newDay.add(dates.get(i));
		}
		return newDay;

	}

	//Organizes dates from earliest to latest
	public static ArrayList<String> dateOrganizer(ArrayList<String> dates) {
		ArrayList<String> daySearch = new ArrayList<String>();
		ArrayList<String> newDay = new ArrayList<String>();
		daySearch = dates;

		while (!daySearch.isEmpty()) {
			String day = daySearch.get(0);
			for (int i = 0; i < dates.size(); i++) {

				day = compareDate(dates.get(i), day);

			}
			newDay.add(day);
			daySearch.remove(day);

		}

		return newDay;
	}
	
	/*returns the later of two dates by comparing first year,
	 *  then month, then day
	 */
	public static String compareDate(String date1, String date2) {
		String date = "";
		int dy1 = yearParse(date1);
		int dy2 = yearParse(date2);
		int dm1 = monthParse(date1);
		int dm2 = monthParse(date2);
		int dd1 = dayParse(date1);
		int dd2 = dayParse(date2);
		int d = compareYear(dy1, dy2);
		if (d == 1) {
			date = date1;
		} else if (d == 2) {
			date = date2;
		} else if (d == 0) {
			d = compareMonth(dm1, dm2);
		}
		if (d == 1) {
			date = date1;
		} else if (d == 2) {
			date = date2;
		} else if (d == 0) {
			d = compareDay(dd1, dd2);
		}
		if (d == 1) {
			date = date1;
		} else if (d == 2) {
			date = date2;
		} else if (d == 0) {
			date = date1;
		}

		return date;
	}

	//Returns true if the first date is larger
	public static boolean dateOneBigger(String date1, String date2) {
		boolean date = true;
		int dy1 = yearParse(date1);
		int dy2 = yearParse(date2);
		int dm1 = monthParse(date1);
		int dm2 = monthParse(date2);
		int dd1 = dayParse(date1);
		int dd2 = dayParse(date2);
		int d = compareYear(dy1, dy2);
		if (d == 1) {
			date = true;
		} else if (d == 2) {
			date = false;
		} else if (d == 0) {
			d = compareMonth(dm1, dm2);
		}
		if (d == 1) {
			date = true;
		} else if (d == 2) {
			date = false;
		} else if (d == 0) {
			d = compareDay(dd1, dd2);
		}
		if (d == 1) {
			date = true;
		} else if (d == 2) {
			date = false;
		} else if (d == 0) {
			date = false;
		}

		return date;
	}
	
	/*Returns an int value to indicate whether one day has a higher value 
	 * than another or if they are equal 
	 */
	
	public static int compareDay(int d1, int d2) {
		int d = 0;
		if (comp(d1, d2) == 1) {
			d = 1;
		} else if (comp(d1, d2) == 2) {
			d = 2;
		} else if (comp(d1, d2) == 0) {
			d = 0;
		}
		return d;

	}

	/*Returns an int value to indicate whether one month has a higher value 
	 * than another or if they are equal 
	 */
	
	public static int compareMonth(int d1, int d2) {
		int d = 0;
		if (comp(d1, d2) == 1) {
			d = 1;
		} else if (comp(d1, d2) == 2) {
			d = 2;
		} else if (comp(d1, d2) == 0) {
			d = 0;
		}
		return d;

	}
	
	/*Returns an int value to indicate whether one year has a higher value 
	 * than another or if they are equal 
	 */

	public static int compareYear(int d1, int d2) {
		int d = 0;
		if (comp(d1, d2) == 1) {
			d = 1;
		} else if (comp(d1, d2) == 2) {
			d = 2;
		} else if (comp(d1, d2) == 0) {
			d = 0;
		}
		return d;
	}

	//parses the year of a date in String into an integer value
	public static int yearParse(String date) {
		String[] nums = date.split("/");
		int year = Integer.parseInt(nums[2]);

		return year;
	}

	//parses the month of a date in String to an integer value
	public static int monthParse(String date) {
		String[] nums = date.split("/");
		int month = Integer.parseInt(nums[0]);

		return month;
	}

	//parses the day of a date in String to an integer value
	public static int dayParse(String date) {
		String[] nums = date.split("/");
		int day = Integer.parseInt(nums[1]);
		return day;
	}

	//Compares two numbers and indicates which is larger
	public static int comp(int a, int b) {
		int c = 0;
		if (a == b) {
			c = 0;
		} else if (a > b) {
			c = 1;
		} else if (b > a) {
			c = 2;
		}
		return c;

	}

}
