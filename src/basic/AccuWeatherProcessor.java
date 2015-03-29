package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.w3c.dom.Document;

import processor.ConnectionProcessor;
import processor.DayDetailsNodeProcessor;
import processor.DayNodeProcessor;
import processor.ProcessorHelper;
import store.Constants;
import store.Details;

public class AccuWeatherProcessor implements Constants {

	private static String logFileLocation = null;

	public static String getLogFileLocation() {
		return logFileLocation;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * http://www.accuweather.com/en/us/colorado-springs-co/80903/current-
		 * weather/327351
		 * http://www.accuweather.com/en/us/colorado-springs-co/80903
		 * /daily-weather-forecast/327351?day=1&unit=c
		 * 
		 */
		String conkyLocation = null;
		if (args[0] == null) {
			System.out
					.println("Please provide the location to .Conky folder...");
			System.exit(1);
		} else {
			conkyLocation = args[0];
		}
		conkyLocation = conkyLocation
				+ "/.Conky/images/accuImage/config/accuWeather.properties";
		String iconsPath = args[0] + "/.Conky/images/accuImage/icons";
		System.out
				.println("Using accuWeather.properties from:" + conkyLocation);

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(conkyLocation));
		} catch (IOException e) {
			System.out.println("FileNotFound..." + conkyLocation);
		}

		String dataFileLocation = properties.getProperty(DATA_FILE_LOCATION);

		logFileLocation = properties.getProperty(LOG_FILE_LOCATION);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss a");

		Date toDayDate = new Date();

		WriteToFile.printToFile(
				dateFormat.format(toDayDate) + NL + NL + SPACER,
				logFileLocation, FALSE);
		String unit = properties.getProperty(UNIT);
		String todayUrl = properties.getProperty(TODAY_URL);

		String finalUrl = todayUrl + CONNECTOR + unit;

		ConnectionProcessor.dataFileLocation = dataFileLocation;

		Document doc = ConnectionProcessor.getDayDocument(finalUrl);

		DayNodeProcessor dayNodeProcessor = new DayNodeProcessor(doc);
		Details dayDetails = dayNodeProcessor.processNode();

		dayDetails.set_day("Today");
		dayDetails.set_iday("day");
		WriteToFile.printToFile(dayDetails.toString(), dataFileLocation
				+ "toDay.txt", FALSE);
		ProcessorHelper.prepareIcon(dayDetails.get_iday() + "day.png",
				dayDetails.get_dayDetails().getIcon(), iconsPath);
		ProcessorHelper.prepareIcon(dayDetails.get_iday() + "night.png",
				dayDetails.get_nightDetails().getIcon(), iconsPath);

		WriteToFile.printToFile(dayDetails.toString(), logFileLocation, TRUE);
		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);
		for (int day = 2; day < 6; day++) {
			String newfinalUrl = finalUrl + "&day=" + day;
			doc = ConnectionProcessor.getDayDocument(newfinalUrl);

			dayNodeProcessor = new DayNodeProcessor(doc);
			dayDetails = dayNodeProcessor.processNode();
			dayDetails.set_day(ProcessorHelper.getDay(day - 1));
			dayDetails.set_iday("day" + day);
			WriteToFile.printToFile(dayDetails.toString(), dataFileLocation
					+ day + ".txt", FALSE);
			ProcessorHelper.prepareIcon(dayDetails.get_iday() + ".png",
					dayDetails.get_dayDetails().getIcon(), iconsPath);
			WriteToFile.printToFile(dayDetails.toString(), logFileLocation,
					TRUE);
			WriteToFile.printToFile(SPACER, logFileLocation, TRUE);

		}

		finalUrl = properties.getProperty(DAYDETAILS_URL) + CONNECTOR + unit;
		doc = ConnectionProcessor.getDayDetailsDocument(finalUrl);
		DayDetailsNodeProcessor dayDetailsNodeProcessor = new DayDetailsNodeProcessor(
				doc);
		dayDetails = dayDetailsNodeProcessor.processNode();

		dayDetails.set_day("DayDetails");
		dayDetails.set_iday("day");
		ProcessorHelper.prepareIcon(dayDetails.get_iday() + "now.png",
				dayDetails.get_dayDetails().getIcon(), iconsPath);

		WriteToFile.printToFile(dayDetails.toStringDayDetails(),
				dataFileLocation + "DayDetails.txt", FALSE);

		WriteToFile.printToFile(dayDetails.toStringDayDetails(),
				logFileLocation, TRUE);
		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);
		finalUrl = properties.getProperty(ASTRO);
		doc = ConnectionProcessor.getMoonDayDocument(finalUrl);

		// MoonPhaseNodeProcessor moonProcessor = new
		// MoonPhaseNodeProcessor(doc);
		// MoonPhases moonPhases = moonProcessor.processNode();
		//
		// WriteToFile.printToFile(moonPhases.toString(), dataFileLocation
		// + "MoonPhase.txt", FALSE);
		// WriteToFile.printToFile(moonPhases.toString(), logFileLocation,
		// TRUE);

		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);

	}
}
