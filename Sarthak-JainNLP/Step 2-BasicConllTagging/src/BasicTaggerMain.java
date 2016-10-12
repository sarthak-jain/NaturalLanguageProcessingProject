import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

public class BasicTaggerMain {

	public static void main(String[] args) throws ClassCastException,
			ClassNotFoundException, IOException {

		String serializedClassifier = "D:/java-eclipse-nlp/NERTagging/classifiers/english.conll.4class.distsim.crf.ser.gz";

		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier
				.getClassifier(serializedClassifier);

		for (int i = 1; i <= 100; i++) {
			String inputFileName = "D:/java-eclipse-nlp/BasicConllTagging/inputFiles/SummaryCollected"
					+ i + ".txt";
//			String inputFileName = "D:/java-eclipse-nlp/BasicConllTagging/Training Set/SummaryCollected"
//					+ i + ".txt";
//			String outputFileName = "D:/java-eclipse-nlp/BasicConllTagging/outputTrainingFiles/Output"
//					+ i + ".txt";

			String outputFileName = "D:/java-eclipse-nlp/BasicConllTagging/outputFiles/Output"
					+ i + ".txt";
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			String fileContents = IOUtils.slurpFile(inputFileName);
			List<List<CoreLabel>> out = classifier.classify(fileContents);
			for (List<CoreLabel> sentence : out) {
				for (CoreLabel word : sentence) {
					output.print(word.word() + '\t'
							+ word.get(CoreAnnotations.AnswerAnnotation.class));
					output.println();
				}
			}
			output.flush();
			output.close();
		}
		System.out.println("Done conll tagging");
	}
}
