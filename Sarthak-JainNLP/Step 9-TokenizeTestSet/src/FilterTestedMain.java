import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class FilterTestedMain {

	public static void main(String[] args) throws IOException {
		for(int f=1;f<=100;f++) {
			String inputFileName = "input/OUTSummaryCollected"+f+".txt";
			String outputFileName = "output/ModelOutput"+f+".txt";
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			try {
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(), StandardCharsets.ISO_8859_1);

				for(int i=0;i<inputList.size();i++) {
					if(!inputList.get(i).equals("")) {
						String splitBySpace[] = inputList.get(i).split(" ");
						for(int j=0;j<splitBySpace.length;j++) {
							String splitCol[] = splitBySpace[j].split("/");
							if(splitCol[0].contains("-LRB-") || splitCol[0].contains("-RRB-")) {
								continue;
							}
							String str = "";
							str = str + splitBySpace[j].substring(0, splitBySpace[j].length()- splitCol[splitCol.length-1].length()-1);
							output.print(str+"\t"+splitCol[splitCol.length-1]);
							if(splitBySpace[j].contains("//")) {
								System.out.println(str+"\t"+splitCol[splitCol.length-1]+"HHHA"+f);
							}

							output.println();
						}
					}
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			output.flush();
			output.close();
//			System.out.println(f);
		}
		System.out.println("done");
	}
}
