import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class FilterRelevantTagFilesMain {

	public static void main(String[] args) throws IOException {
		for(int f=1;f<=100;f++) {
			String inputFileName = "input/ModelOutput"+f+".txt";
			String outputFileName = "output/ExtractedOutput"+f+".txt";
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			try {
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(), StandardCharsets.ISO_8859_1);

				String org = "ORGANIZATION: ";
				String loc = "LOCATION: ";
				String lang = "LANGUAGES: ";
				String tools = "TOOLS: ";
				String exp = "EXPERIENCE: ";
				String deg = "DEGREE: ";
				String title = "TITLE: ";
				for(int i=0;i<inputList.size();i++) {
					if(!inputList.get(i).equals("")) {
						String split[] = inputList.get(i).split("\t");
				
						if(split[1].equalsIgnoreCase("ORGANIZATION")) {
							org = org + split[0] + ",";
						} else if(split[1].equalsIgnoreCase("TITLE")) {
							title = title + split[0] + ",";
						} else if(split[1].equalsIgnoreCase("LOCATION")) {
							loc = loc + split[0] + ",";
						} else if(split[1].equalsIgnoreCase("LANG")) {
							lang = lang + split[0]+ ",";
						} else if(split[1].equalsIgnoreCase("TOOLS")) {
							tools = tools + split[0]+ ",";
						} else if(split[1].equalsIgnoreCase("EXP")) {
							exp = exp + split[0] + ",";
						} else if(split[1].equalsIgnoreCase("DEGREE")) {
							deg = deg + split[0] + ",";
						} 
					}
				}
				String out = org.substring(0, org.length()-1) + ".\n" + loc.substring(0, loc.length()-1) + ".\n" + title.substring(0, title.length()-1) + ".\n" + deg.substring(0, deg.length()-1) + ".\n" + lang.substring(0, lang.length()-1) + ".\n" + tools.substring(0, tools.length()-1) + ".\n" + exp.substring(0, exp.length()-1) + ".\n";
				output.print(out);
				output.println();
				System.out.println(out);
			}catch(Exception e) {
				System.out.println(e);
			}
			output.flush();
			output.close();
			System.out.println(f);
		}
		System.out.println("done");
	}
}
