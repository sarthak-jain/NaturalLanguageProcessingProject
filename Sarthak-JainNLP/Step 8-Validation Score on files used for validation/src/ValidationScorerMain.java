import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class ValidationScorerMain {

	public static void main(String[] args) throws IOException {
		int gpos =0;
		int gneg=0;
		int gtotal=0;
		String outputFileName = "ScoreOn100filesModel1000";
		PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
		for(int f=1001;f<=1100;f++) {
			String inputFileName = "ValidatedSet100/OUTMergedOutput"+f+".train";
			try {
				int pos=0;
				int neg=0;
				File inputFile = new File(inputFileName);
				List<String> inputList = Files.readAllLines(inputFile.toPath(), StandardCharsets.ISO_8859_1);
				int total=inputList.size();
				gtotal = gtotal + total;
				for(int i=0;i<inputList.size();i++) {
					if(!inputList.get(i).equals("")) {
						String str[] = inputList.get(i).split("\t");
						if(str[1].equals(str[2])) {
							pos = pos+1;
						} else {
							neg = neg+1;
						}
					}
				}
				int posPer = 100*pos/total;
				System.out.println("File"+f+": total:"+total+" pos:"+pos+" neg:"+neg+" match:"+posPer);
				output.print("File"+f+": total:"+total+" pos:"+pos+" neg:"+neg+" match:"+posPer);
				output.println();
				gpos = gpos+pos;
				gneg=gneg+neg;
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		System.out.println();
		output.println();
		int gposPer = 100*gpos/gtotal;
		System.out.println("Validation: "+" total:"+gtotal+" pos:"+gpos+" neg:"+gneg+" match:"+gposPer);
		output.println("Validation: "+" total:"+gtotal+" pos:"+gpos+" neg:"+gneg+" match:"+gposPer);
		output.flush();
		output.close();
		System.out.println("done");
		
	}

}
