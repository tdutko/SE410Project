package storybook.ingest;

import java.util.List;
import java.util.Map;

import storybook.ingest.StanfordCharacterParser.Strictness;

public class Test {

	public static void main(String[] args){
		String filePath = "C:/Users/Mordio/Desktop/test.txt";
		Ingestor ing = new TextFileIngestor(filePath);
		Parser parser = new StanfordCharacterParser(ing.getContent(),Strictness.FIRSTLAST);
		List<String> characters = parser.getTokens();
		Map<String,String> map = Genderer.genderCharacters(characters);
		
		System.out.println("Done");
	}
}
