/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * This Class is used for Monitoring the error messages and redirecting those to
 * the ErrorLog.txt File.
 * 
 * @author amit.kshirsagarindiatimes.com
 */
public class WriteToFile {
	/**
	 * This Method is static.
	 * <p>
	 * It takes error message as argument.
	 * <p>
	 * public static void toMonitorFile(String errorMassage)
	 */

	private static FileWriter fileWriter = null;

	public static void printToFile(String writeMassage, String reportFile,
			boolean append) {
		if (writeMassage.equals("")) {
			return;
		}
		File errorFile = new File(reportFile);
		try {
			fileWriter = new FileWriter(errorFile, append);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(writeMassage);

			fileWriter.close();
		} catch (Exception e) {
			System.out.println("Error In writing the Error output to the File"
					+ e.getMessage());
		}
	}

	public static void closeFileWriter() {
		try {
			fileWriter.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}