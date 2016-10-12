import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ManualTagger {

	public static void main(String[] args) throws IOException {
		String languageFileName = "helperFiles/Languages.txt";
		String toolsFileName = "helperFiles/Tools.txt";
		String numberFileName = "helperFiles/Number.txt";
		String titleFileName = "helperFiles/Titles.txt";

		File langFile = new File(languageFileName);
		List<String> langList = Files.readAllLines(langFile.toPath(),
				StandardCharsets.ISO_8859_1);
		File toolsFile = new File(toolsFileName);
		List<String> toolsList = Files.readAllLines(toolsFile.toPath(),
				StandardCharsets.ISO_8859_1);
		File numberFile = new File(numberFileName);
		List<String> numberList = Files.readAllLines(numberFile.toPath(),
				StandardCharsets.ISO_8859_1);
		File titleFile = new File(titleFileName);
		List<String> titleList = Files.readAllLines(titleFile.toPath(),
				StandardCharsets.ISO_8859_1);

		for (int f = 1; f <= 100; f++) {
			String inputFileName = "inputFiles/Output" + f + ".txt";
			
			String outputFile = "outputFiles/ManOutput" + f + ".txt";
			try {
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(),
						StandardCharsets.ISO_8859_1);
				List<String> inputs = new ArrayList<String>();
				for (int a = 0; a < inputList.size(); a++) {
					if (inputList.get(a).contains("-LRB-")
							|| inputList.get(a).contains("-RRB-")) {
						continue;
					}
					inputs.add(inputList.get(a));

				}
				PrintWriter output = new PrintWriter(new FileWriter(outputFile));
				for (int i = 0; i < inputs.size(); i++) {
					if (!inputs.get(i).equals("")) {
						String splitInput[] = inputs.get(i).split("\t");
						int toolLength = 0;
						boolean toolMatch = false;
						for (int j = 0; j < toolsList.size(); j++) {
							String toolsSplit[] = toolsList.get(j).split(" ");
							toolMatch = false;
							int inputIndex = i;
							for (int k = 0; k < toolsSplit.length; k++) {
								String toolInput[] = inputs.get(inputIndex)
										.split("\t");
								if (toolInput[0].toUpperCase().equalsIgnoreCase(
										toolsSplit[k].toUpperCase())) {
									inputIndex++;
									toolLength = toolsSplit.length;
									toolMatch = true;
								} else {
									toolMatch = false;
									break;
								}
							}
							if (toolMatch == true) {
								break;
							}
						}
						if (toolMatch == true) {
							for (int j = 0; j < toolLength; j++) {
								String str = "";
								str = str + inputs.get(i) + "\t" + "TOOLS";
								output.print(str);
								output.println();
								i++;
							}
							i = i - 1;
						} else {
							String write = "";
							if (titleList.contains(splitInput[0].toUpperCase())) {
								write = write + inputs.get(i) + "\t" + "TITLE";
							} else if (langList.contains(splitInput[0])) {
								write = write + inputs.get(i) + "\t" + "LANG";
							} else {
								if (i < inputs.size() - 1) {
									String splitNext[] = inputs.get(i + 1)
											.split("\t");
									if (i < inputs.size() - 1
											&& (splitInput[0].equals("years")
													|| splitNext[0]
															.equals("years")
													|| splitInput[0]
															.equals("/")
													|| splitInput[0]
															.equals("year")
													|| splitNext[0]
															.equals("year")
													|| splitInput[0]
															.equalsIgnoreCase("experience") || numberList
														.contains(splitInput[0]
																.toUpperCase()))) {

										write = write + inputs.get(i) + "\t"
												+ "EXP";
									} else {
										if (splitInput[0].toUpperCase()
												.contains("BACHELOR")
												|| splitInput[0].toUpperCase()
														.contains("B.S.")
												|| splitInput[0].toUpperCase()
														.contains("BS")

												|| splitInput[0].toUpperCase()
														.contains("MASTER")
												|| splitInput[0].toUpperCase()
														.contains("PHD")
												|| splitInput[0].toUpperCase()
														.contains("DEGREE")) {
											write = write + inputs.get(i)
													+ "\t" + "DEGREE";
										} else {
											write = write + inputs.get(i)
													+ "\t" + "O";
										}
									}
								} else if (i == inputs.size() - 1) {
									if (splitInput[0].equals("years")
											|| splitInput[0].equals("year")) {
										write = write + inputs.get(i) + "\t"
												+ "EXP";
									} else if (splitInput[0].toUpperCase().contains(
											"BACHELOR")
											|| splitInput[0].toUpperCase()
													.contains("MASTER")
											|| splitInput[0].toUpperCase()
													.contains("PHD")
											|| splitInput[0].toUpperCase()
													.contains("DEGREE")) {
										write = write + inputs.get(i) + "\t"
												+ "DEGREE";
									} else {
										write = write + inputs.get(i) + "\t"
												+ "O";
									}

								}
							}
							output.print(write);
							output.println();
						}
					}
				}
				output.flush();
				output.close();
			} catch (Exception e) {
				System.out.println("exception e:" + e);
			}
			System.out.println(f);
		}
		System.out.println("done");
	}
}
