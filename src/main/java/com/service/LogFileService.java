package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.ServletConfig;

public class LogFileService {
	private static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine" };
	private static final String[] teens = { "", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };
	private static final String[] tens = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
			"Eighty", "Ninety" };

	public static String convertToWords(float number) {
		if (number == 0) {
			return "Zero";
		}
		return convertToWordsHelper(number).trim();
	}

	private static String convertToWordsHelper(float number) {
		String result = "";

		if (number < 10) {
			result = units[(int) number];
		} else if (number < 20) {
			result = teens[(int) (number - 10)];
		} else if (number < 100) {
			result = tens[(int) (number / 10)] + " " + units[(int) (number % 10)];
		} else if (number < 1000) {
			result = units[(int) (number / 100)] + " Hundred " + convertToWordsHelper(number % 100);
		} else if (number < 1000000) {
			result = convertToWordsHelper(number / 1000) + " Thousand " + convertToWordsHelper(number % 1000);
		} else if (number < 1000000000) {
			result = convertToWordsHelper(number / 1000000) + " Million " + convertToWordsHelper(number % 1000000);
		} else {
			result = convertToWordsHelper(number / 1000000000) + " Billion "
					+ convertToWordsHelper(number % 1000000000);
		}

		return result;
	}

	public static String changeFormate(String date, String currentFormate, String reqFormate) {

		try {
			// we may write simple date format on place of dateFormate
			DateFormat srcDf = new SimpleDateFormat(currentFormate);
			// parse the date string into Date object
			Date dat = srcDf.parse(date);
			DateFormat destDf = new SimpleDateFormat(reqFormate);
			// format the date into another format
			date = destDf.format(dat);
			// System.out.println("Converted date is : " + date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getEndDates(String formate, int mark) {

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		String date1 = null, date2 = null;
		System.out.println(mark);

		switch (mark) {

		case 1: {
			// ------ get today's date

			cal.set(Calendar.DATE, cal.get(Calendar.DATE));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());
			System.out.println(new SimpleDateFormat(formate).format(cal.getTime()));
			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());
			System.out.println(new SimpleDateFormat(formate).format(cal2.getTime()));

			break;
		}
		case 2: {
			// ------ get yesterday's date
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - 1);
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		case 3: {
			// ------ get this week end points
			int dayVal = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayVal);

			/*
			 * System.out.println("a "+ Calendar.DATE); System.out.println("b "+
			 * cal.get(Calendar.DATE)); System.out.println("c "+ (cal.get(Calendar.DATE) -
			 * (dayVal - 1))); System.out.println("d1 "+ cal2.get(Calendar.DATE));
			 * System.out.println("d "+ (cal2.get(Calendar.DATE) - (dayVal - 7)));
			 * System.out.println("f "+ cal.getTime()); System.out.println("g "+
			 * cal2.getTime());
			 */

			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - (dayVal - 1));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - (dayVal - 7));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		case 4: {
			// ------ get this week's end points
			int dayVal = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayVal);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - (7 + dayVal - 1));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - (dayVal));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}

		case 5: {
			// ------ get this Month's end points

			cal.set(Calendar.DATE, 1);
			// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			// cal2.set(Calendar.MONTH, cal2.get(Calendar.MONTH) - 1);
			cal2.set(Calendar.DATE, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));

			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}

		case 6: {
			// ------ get this week end points

			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.MONTH, 0);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, 31);
			cal2.set(Calendar.MONTH, 11);
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		default:
			break;
		}

		return date1 + "," + date2;
	}

	public static String changeFormateOfCurrentDate(String formate) {

		Calendar cal = Calendar.getInstance();
		// Date dat = new Date();
		// DateFormat destDf = new SimpleDateFormat(formate);

		SimpleDateFormat destDf = new SimpleDateFormat(formate);

		// format the date into another format
		// System.out.println("Converted date is : " +
		// destDf.format(cal.getTime()));
		String result = destDf.format(cal.getTime());
		return result;
	}
	
	public static void catchLogFile(Exception e, ServletConfig config) throws IOException {
		String date1 = changeFormateOfCurrentDate("yyyy-MM-dd");
		String time = changeFormateOfCurrentDate("HH:mm");
		e.printStackTrace();
		File file = new File(
				config.getServletContext().getRealPath("/") + "catchLogs/SqlCatchLogFile" + date1 + ".txt"); // Your
																												// file

		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file, true);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		new Scanner(System.in);
		System.out.print(" [" + date1 + " " + time + "] ");
		e.printStackTrace(System.out);
		fos.close();
	}

	public static String previousDateString(String dateString) throws ParseException {
		// Create a date formatter using your format string
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the given date string into a Date object.
		// Note: This can throw a ParseException.
		Date myDate = dateFormat.parse(dateString);

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		/* calendar.add(Calendar.DAY_OF_YEAR, -1); */
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		String result = dateFormat.format(previousDate);

		return result;
	}

	public static void sendMsg(String message, String mobiles) {
		// Your authentication key
		String authkey = "51723AkI1gSXU5280e463";

		// Sender ID,While using route4 sender id should be 6 characters long.
		String senderId = "THRSTY";

		// define route
		String gwid = "2";

		// Prepare Url
		URLConnection myURLConnection = null;
		URL myURL = null;
		BufferedReader reader = null;

		// encoding message
		String encoded_message = URLEncoder.encode(message);

		// Send SMS API

		// http://103.26.99.76/vendorsms/pushsms.aspx?user=abc&password=xyz&msisdn=919898xxxxxx,919898xxxxxx&sid=SenderId&msg=test%20message&fl=0&gwid=2

		String mainUrl = "http://103.26.99.76/vendorsms/pushsms.aspx?user=suraj&password=suraj";

		// String mainUrl = "http://54.254.154.166/sendhttp.php?";

		// Prepare parameter string
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		// sbPostData.append("authkey=" + authkey);
		sbPostData.append("&msisdn=" + mobiles);
		sbPostData.append("&sid=" + senderId);
		sbPostData.append("&msg=" + encoded_message);
		sbPostData.append("&fl=" + "0");
		sbPostData.append("&gwid=" + gwid);

		// sbPostData.append("&route=" + route);

		// final string
		mainUrl = sbPostData.toString();
		try {
			// prepare connection
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			// reading response
			String response;
			while ((response = reader.readLine()) != null)
				// print response
				System.out.println(response);
			System.out.println(mainUrl);
			// finally close connection
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String formatDateTime(String inputDateTime) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat outputTimeFormat = new SimpleDateFormat("h:mm a", Locale.US);

		try {
			Date date = inputFormat.parse(inputDateTime);
			String formattedDate = outputDateFormat.format(date);
			String formattedTime = outputTimeFormat.format(date);

			// Capitalize AM/PM
			formattedTime = formattedTime.replace("am", "AM").replace("pm", "PM");

			return formattedDate + " " + formattedTime;
		} catch (ParseException e) {
			e.printStackTrace();
			return "Error: Invalid input format";
		}
	}

	public static String formatDateTimeForSql(String inputDateTime) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = inputFormat.parse(inputDateTime);

			// Check if seconds are missing, and provide a default of "00"
			if (!inputDateTime.endsWith(":")) {
				inputDateTime += ":00";
			}

			return outputFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Return null or handle the error as needed
		}
	}

}
