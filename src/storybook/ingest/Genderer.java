package storybook.ingest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.irobson.jgenderize.GenderizeIoAPI;
import com.github.irobson.jgenderize.client.Genderize;

public class Genderer {

	public static Map<String,String> genderCharacters(List<String> names){
		Map<String,String> characterMap = new HashMap<String,String>();
		Genderize api = GenderizeIoAPI.create();
		
		for (String name : names){
			String firstName = name.split(" ")[0];
			String gender = api.getGender(firstName).getGender();
			characterMap.put(name, gender);
		}
		
		return characterMap;
	}
	
}
