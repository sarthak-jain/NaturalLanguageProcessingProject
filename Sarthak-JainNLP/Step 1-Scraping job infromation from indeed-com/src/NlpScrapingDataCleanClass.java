import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.text.WrappedPlainView;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * This file is to scrape data from indeed using the collected urls.
 */
public class NlpScrapingDataCleanClass {

	private NlpScrapingDataCleanClass() {
	}

	public static void extractText() throws IOException {

		BufferedReader readURL = new BufferedReader(new FileReader(new File(
				"urlsCollectedUnique.txt")));
		ArrayList<String> listOfUrls = new ArrayList<String>();
		String lineUrl = "";
		while ((lineUrl = readURL.readLine()) != null) {
			if (lineUrl.equals("")) {
				continue;
			}
			listOfUrls.add(lineUrl);
		}

		int presentNum = 0;
		int notPresentNum = 0;
		System.out.println(listOfUrls.size() + " is list size");
FileWriter easyApplyUrls = new FileWriter("easyApplyURLs.txt",true);
		FileWriter correspondingDetailsWriter = new FileWriter(
				"correspondingDetails.txt");
		try {
			int count = 0;
			for (String url : listOfUrls) {
				count++;
				if (url.contains("mailto")) {
					continue;
				}
				System.out.println(count);
				// System.out.println(url);
				try {
					Document doc = Jsoup.connect(url).get();
					// retrieving the information only if it is an indeed.com
					// easily
					// apply page

					Element indeedApplyExists = null;
					indeedApplyExists = doc.getElementById("indeed_apply");

					if (indeedApplyExists == null) {
						notPresentNum++;

					} else {
						presentNum++;
						String newSummary = doc.getElementsByClass("summary")
								.text();
						String companyName = doc.getElementsByClass("company")
								.text();
						String location = doc.getElementsByClass("location")
								.text();
						String str = "SummaryCollected" + presentNum + ".txt";

						System.out.println("------summary found-----");
						FileWriter writer = new FileWriter(
								"C:/sarthak/Github-Bitbucket/NLP/NlpScrapingData/jobSummaries/"
										+ str);
						System.out.println("------summary found-----");
						correspondingDetailsWriter.write(str + "\t"
								+ companyName + "\t" + location);
						correspondingDetailsWriter.write("\n");
						easyApplyUrls.write(url);
						easyApplyUrls.write("\n");
						easyApplyUrls.flush();
						writer.write(newSummary);
						writer.write("\n");
						writer.flush();
						writer.close();
						correspondingDetailsWriter.flush();
					}

				} catch (SocketTimeoutException e) {
					continue;
				} catch (HttpStatusException e) {
					continue;
				}catch (SSLHandshakeException e){
					continue;
				} catch (Exception e) {
					continue;
				}
				
			}
		} catch (Exception ex) {
			System.out.println("Exception is : " + ex);
			ex.printStackTrace();
		}
		System.out.println(presentNum + " are present");
		System.out.println(notPresentNum + "   are not present");

		correspondingDetailsWriter.flush();
		correspondingDetailsWriter.close();

	}

	public final static void main(String[] args) throws Exception {

		NlpScrapingDataCleanClass.extractText();

	}
}