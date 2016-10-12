import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class Files2000Main {

	public static void main(String[] args) {

		String finalFileName = "file1000.train";
		try {
			PrintWriter finalFile = new PrintWriter(new FileWriter(finalFileName));
			
			String str = "";
			for (int f = 2001; f <= 3000; f++) {
				String inputFileName1 = "input1000/MergedOutput"+f+".train";
				File inputFile1 = new File(inputFileName1);
				List<String> inputList1 = Files.readAllLines(inputFile1.toPath(), StandardCharsets.ISO_8859_1);
				for(int j=0;j<inputList1.size();j++) {
					finalFile.print(inputList1.get(j));
					finalFile.println();
				}
				finalFile.println();
			}
			finalFile.flush();
			finalFile.close();
			System.out.println("done");
		} catch(Exception e) {
			System.out.println(e);
		}

	}

}
