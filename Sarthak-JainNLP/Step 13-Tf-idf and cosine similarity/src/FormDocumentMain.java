import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class FormDocumentMain {
	public static void main(String []args) {
		for(int i=0;i<args.length;i++) {
				try{
					File input = new File(args[i]);
					List<String> in = Files.readAllLines(input.toPath(), StandardCharsets.ISO_8859_1);
					String out = in.toString();
					System.out.println("out is: "+out);
				} catch(Exception e) {
					System.out.println(e);
				}
		}
		
//		try {
//			File extracted = new File("ExtractedResume.txt");
//			List<String> in = Files.readAllLines(extracted.toPath(), StandardCharsets.ISO_8859_1);
//			String out = in.toString();
//			System.out.println("out is: "+out);
//		} catch(Exception e) {
//			System.out.println(e);
//		}
//	

	}
}
