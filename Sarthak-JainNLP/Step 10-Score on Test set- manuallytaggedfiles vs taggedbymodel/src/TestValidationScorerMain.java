import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class TestValidationScorerMain {

	public static void main(String[] args) throws IOException {
		int gpos = 0;
		int gneg = 0;
		int gtotal = 0;
		String outputFileName = "ScoreOn100TestfilesModel1000";
		PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
		for (int f = 1; f <= 100; f++) {
			String inputFileName1 = "HandInputs/MergedOutput" + f + ".train";
			String inputFileName2 = "ModelInputs/ModelOutput" + f + ".txt";
			try {
				int pos = 0;
				int neg = 0;
				File inputFile1 = new File(inputFileName1);
				List<String> inputList1 = Files.readAllLines(
						inputFile1.toPath(), StandardCharsets.ISO_8859_1);
				File inputFile2 = new File(inputFileName2);
				List<String> inputList2 = Files.readAllLines(
						inputFile2.toPath(), StandardCharsets.ISO_8859_1);
				if (inputList1.size() == inputList2.size()) {

					int total = inputList1.size();
					gtotal = gtotal + total;
					for (int i = 0; i < inputList1.size(); i++) {
						String col1[] = inputList1.get(i).split("\t");
						String col2[] = inputList2.get(i).split("\t");

						if (col1[1].equals(col2[1])) {
							pos = pos + 1;
						} else {
							neg = neg + 1;
						}
					}
					int posPer = 100 * pos / total;
					System.out.println("File" + f + ": total:" + total
							+ " pos:" + pos + " neg:" + neg + " match:"
							+ posPer);
					output.print("File" + f + ": total:" + total + " pos:"
							+ pos + " neg:" + neg + " match:" + posPer);
					output.println();
					gpos = gpos + pos;
					gneg = gneg + neg;

				} else {
					System.out.println("Unequal files" + f);
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println();
		output.println();
		int gposPer = 100 * gpos / gtotal;
		System.out.println("TestValidation: " + " total:" + gtotal + " pos:" + gpos
				+ " neg:" + gneg + " match:" + gposPer);
		output.println("TestValidation: " + " total:" + gtotal + " pos:" + gpos
				+ " neg:" + gneg + " match:" + gposPer);
		output.flush();
		output.close();
		System.out.println("done");

	}

}
