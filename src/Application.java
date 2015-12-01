import java.io.IOException;

import opennlp.tools.util.Span;


public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelperOpenNLP HelperOpenNLP_obj =new HelperOpenNLP();
		String input = "This is a sample sentence";
		String tokens[]=HelperOpenNLP_obj.Tokenize(input);
		try {
			HelperOpenNLP_obj.POSTag(tokens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
