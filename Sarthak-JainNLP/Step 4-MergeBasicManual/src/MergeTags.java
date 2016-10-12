import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class MergeTags {
	public static void main(String[] args) {
		for(int f=1;f<=100;f++) {
			String inputFileName = "inputFiles/ManOutput"+f+".txt";
			String outputFileName = "outputFiles/MergedOutput"+f+".train";
			try {
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(), StandardCharsets.ISO_8859_1);
				PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
				for(int i=0;i<inputList.size();i++) {
					if(!inputList.get(i).equals("")) {
						String col[] = inputList.get(i).split("\t");
						if(!col[2].equalsIgnoreCase("O")) {
							output.print(col[0]+"\t"+col[2]);
						} else {
							output.print(col[0]+"\t"+col[1]);
						}
						output.println();
					}
				}
				output.flush();
				output.close();
			} catch(Exception e) {
				System.out.println("Exception e:"+e);
			}
			System.out.println(f);
		}
		System.out.println("done");
	}
}
