package processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import store.Constants;
import basic.AccuWeatherProcessor;
import basic.WriteToFile;

public class ConnectionProcessor implements Constants {
	public static String dataFileLocation = null;

	public static Document getMoonDayDocument(String url) {
		// System.out.println(url);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();

			HttpURLConnection urlc = (HttpURLConnection) new URL(url)
					.openConnection();
			urlc.setAllowUserInteraction(false);
			urlc.setDoInput(true);
			urlc.setDoOutput(false);
			urlc.setUseCaches(true);
			urlc.setRequestMethod(httpRequestType);
			urlc.connect();
			// check you have received an status code 200 to indicate OK
			// get the encoding from the Content-Type header
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			boolean startBuffering = false;
			int counter = 0;
			stringBuilder.append(xmlOpener);
			while ((line = in.readLine()) != null) {

				boolean firstCount = false;
				if (line.contains("div class=\"moon-phase\"")) {
					startBuffering = true;
					firstCount = true;
					counter++;
				}
				while (startBuffering && counter > 0) {

					if (line.contains(oDiv) && line.contains(cDiv)) {
						WriteToFile
								.printToFile(EMPTYSTRING, AccuWeatherProcessor
										.getLogFileLocation(), TRUE);
					} else if (line.contains(oDiv)) {
						if (firstCount) {
							firstCount = false;
						} else {
							counter++;
						}
					} else if (line.contains(cDiv)) {
						counter--;
					}
					if (line.contains("<h6")) {
						line = line.replaceAll("<h6", "<h6 src=\"blah\"");
					}

					line = line.replaceAll("&[a-z]+;", "");
					stringBuilder.append(line);
					line = in.readLine();

				}
			}

			stringBuilder.append(xmlCloser);

			String finalDocString = stringBuilder.toString();
			finalDocString = finalDocString.replaceAll("&#[0-9]+;", "&amp;");
			finalDocString = finalDocString.replaceAll("<", "\n<").replace(
					"\n<?xml", "<?xml");
			WriteToFile.printToFile(finalDocString, dataFileLocation
					+ "/sampleData/accuWeatherMoonDocument.xml", false);

			dbf = DocumentBuilderFactory.newInstance();
			// db = dbf.newDocumentBuilder();
			// InputSource is = new InputSource();
			// is.setCharacterStream(new StringReader(finalDocString));
			// doc = db.parse(is);
			db = dbf.newDocumentBuilder();
			doc = db.parse(dataFileLocation
					+ "/sampleData/accuWeatherMoonDocument.xml");

		} catch (Exception e) {
			WriteToFile.printToFile(e.getMessage(),
					AccuWeatherProcessor.getLogFileLocation(), TRUE);
		}
		return doc;
	}

	public static Document getDayDocument(String url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();

			HttpURLConnection urlc = (HttpURLConnection) new URL(url)
					.openConnection();
			urlc.setAllowUserInteraction(false);
			urlc.setDoInput(true);
			urlc.setDoOutput(false);
			urlc.setUseCaches(true);
			urlc.setRequestMethod(httpRequestType);
			urlc.connect();
			// check you have received an status code 200 to indicate OK
			// get the encoding from the Content-Type header
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			boolean startBuffering = false;
			int counter = 0;
			stringBuilder.append(xmlOpener);
			while ((line = in.readLine()) != null) {

				boolean firstCount = false;
				if (line.contains("div id=\"detail-day-night\" class=\"detail-tab-panel\"")) {
					startBuffering = true;
					firstCount = true;
					counter++;
				}
				boolean scriptLine = false;
				while (startBuffering && counter > 0) {

					if (line.contains(oDiv) && line.contains(cDiv)) {
						WriteToFile
								.printToFile(EMPTYSTRING, AccuWeatherProcessor
										.getLogFileLocation(), TRUE);
					} else if (line.contains(oDiv)) {
						if (firstCount) {
							firstCount = false;
						} else {
							counter++;
						}
					} else if (line.contains(cDiv)) {
						counter--;
					}

					line = line.replaceAll("&[a-z]+;", "");

					if (line.contains("<script")) {
						scriptLine = true;
					}

					if (!scriptLine) {
						// System.out.println(line);
						stringBuilder.append(line);
					} else {
						// System.out.println(line);
					}

					if (line.contains("</script")) {
						scriptLine = false;
					}
					line = in.readLine();
				}
			}

			stringBuilder.append(xmlCloser);
			String finalDocString = stringBuilder.toString();
			finalDocString = finalDocString.replaceAll("&", "&amp;");
			finalDocString = finalDocString.replaceAll("<", "\n<").replace(
					"\n<?xml", "<?xml");
			WriteToFile.printToFile(finalDocString, dataFileLocation
					+ "/sampleData/accuWeatherDayDocument.xml", false);

			dbf = DocumentBuilderFactory.newInstance();
			// db = dbf.newDocumentBuilder();
			// InputSource is = new InputSource();
			// is.setCharacterStream(new StringReader(finalDocString));
			// doc = db.parse(is);
			db = dbf.newDocumentBuilder();
			doc = db.parse(dataFileLocation
					+ "/sampleData/accuWeatherDayDocument.xml");
		} catch (Exception e) {
			WriteToFile.printToFile(e.getMessage(),
					AccuWeatherProcessor.getLogFileLocation(), TRUE);
		}
		return doc;
	}

	public static Document getDayDetailsDocument(String url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();

			HttpURLConnection urlc = (HttpURLConnection) new URL(url)
					.openConnection();
			urlc.setAllowUserInteraction(false);
			urlc.setDoInput(true);
			urlc.setDoOutput(false);
			urlc.setUseCaches(true);
			urlc.setRequestMethod(httpRequestType);
			urlc.connect();
			// check you have received an status code 200 to indicate OK
			// get the encoding from the Content-Type header
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			boolean startBuffering = false;
			int counter = 0;
			stringBuilder.append(xmlOpener);
			while ((line = in.readLine()) != null) {

				boolean firstCount = false;
				if (line.contains("div id=\"detail-now\" class=\"detail-tab-panel day\"")
						|| line.contains("div id=\"detail-now\" class=\"detail-tab-panel night\"")) {
					startBuffering = true;
					firstCount = true;
					counter++;
				}
				
				if (line.contains("span class=\"current-city\"")){
					stringBuilder.append("<div class=\"more-info-content\">");
					stringBuilder.append(line);
					stringBuilder.append("</div>");
				}
				while (startBuffering && counter > 0) {

					if (line.contains(oDiv) && line.contains(cDiv)) {
						WriteToFile
								.printToFile(EMPTYSTRING, AccuWeatherProcessor
										.getLogFileLocation(), TRUE);
					} else if (line.contains(oDiv)) {
						if (firstCount) {
							firstCount = false;
						} else {
							counter++;
						}
					} else if (line.contains(cDiv)) {
						counter--;
					}

					line = line.replaceAll("&[a-z]+;", "");
					stringBuilder.append(line);
					line = in.readLine();

				}
			}

			stringBuilder.append(xmlCloser);

			String finalDocString = stringBuilder.toString();
			finalDocString = finalDocString.replaceAll("&", "&amp;");
			finalDocString = finalDocString.replaceAll("<", "\n<").replace(
					"\n<?xml", "<?xml");
			WriteToFile.printToFile(finalDocString, dataFileLocation
					+ "/sampleData/accuWeatherDayDetailsDocument.xml", false);

			dbf = DocumentBuilderFactory.newInstance();
			// db = dbf.newDocumentBuilder();
			// InputSource is = new InputSource();
			// is.setCharacterStream(new StringReader(finalDocString));
			// doc = db.parse(is);
			db = dbf.newDocumentBuilder();
			doc = db.parse(dataFileLocation
					+ "/sampleData/accuWeatherDayDetailsDocument.xml");
		} catch (Exception e) {
			WriteToFile.printToFile(e.getMessage(),
					AccuWeatherProcessor.getLogFileLocation(), TRUE);
		}
		return doc;
	}

}
