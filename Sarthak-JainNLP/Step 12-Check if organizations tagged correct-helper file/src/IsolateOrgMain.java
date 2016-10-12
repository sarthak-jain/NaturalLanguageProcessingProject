import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class IsolateOrgMain {

	public static void main(String[] args) throws IOException {
		String helperFileName = "helperfile/correspondingDetails.txt";
		List<String> helperList;
		List<String> helpList = new ArrayList<String>();
		try {
			File helperFile = new File(helperFileName);
			helperList = Files.readAllLines(helperFile.toPath(), StandardCharsets.ISO_8859_1);
			for(int h=0;h<=99;h++) {
				String splitHelp[] = helperList.get(h).split("\t");
				String cmp[] = splitHelp[1].split(",");
				helpList.add(cmp[0]);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		for(int f=1;f<=100;f++) {
			String inputFileName = "input/ExtractedOutput"+f+".txt";
			String outputFileName = "output/FinalOutput"+f+".txt";
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			try {
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(), StandardCharsets.ISO_8859_1);
				String splitInput[] = inputList.get(0).substring(14, inputList.get(0).length()).split(",");
				String org = "ORGANIZATION: ";
				for(int i=0;i<splitInput.length;i++) {

					if(helpList.get(f-1).contains(splitInput[i])) {
						org = org + splitInput[i] + ",";
					}
				}

				output.print(org.substring(0, org.length()-1));
				output.println();
				for(int j=1;j<inputList.size();j++) {
					output.print(inputList.get(j));
					output.println();
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			System.out.println(f);
			output.flush();
			output.close();
		}
		System.out.println("done");
	}
}
