import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class HelperOpenNLP {

	public HelperOpenNLP() {

	}

	// 1.Function Sentence Detector
	public static String[] SentenceDetect(String Str) {
		InputStream is;
		String sentences[];
		try {
			is = new FileInputStream("model/en-sent.bin");
			SentenceModel model;
			try {
				model = new SentenceModel(is);
				SentenceDetectorME sdetector = new SentenceDetectorME(model);
				sentences = sdetector.sentDetect(Str);
				is.close();
				return sentences;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 2.Function Tokenizer
	public static String[] Tokenize(String str) {
		InputStream is;
		String tokens[];
		try {
			is = new FileInputStream("model/en-token.bin");
			TokenizerModel model;
			try {
				model = new TokenizerModel(is);
				Tokenizer tokenizer = new TokenizerME(model);
				tokens = tokenizer.tokenize(str);
				is.close();
				return tokens;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 3.Function NameEnityFinder
	public static Span[] findName(String str[]) {
		InputStream is;
		Span names[];
		try {
			is = new FileInputStream("model/en-ner-person.bin");
			TokenNameFinderModel model;
			try {
				model = new TokenNameFinderModel(is);
				is.close();
				NameFinderME nameFinder = new NameFinderME(model);
				names = nameFinder.find(str);
				return names;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// 4.Function POS Tagger
	public static void POSTag(String str[]) throws IOException {
		POSModel model = new POSModelLoader().load(new File(
				"model/en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);

		perfMon.start();
			String[] tags = tagger.tag(str);
			//System.out.println(whitespaceTokenizerLine[0]);
			POSSample sample = new POSSample(str, tags);
			System.out.println(sample);

		perfMon.incrementCounter();
		//perfMon.stopAndPrintFinalResult();
	}

}
