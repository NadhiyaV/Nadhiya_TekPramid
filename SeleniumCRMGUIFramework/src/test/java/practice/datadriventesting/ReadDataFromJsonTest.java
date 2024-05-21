package practice.datadriventesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//step 1: parse Json Physical file into Java Object using JsonParse class
		JSONParser parser =new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\DELL\\Desktop\\appCommonData1.json"));
		
		//step 2: covert javaobject into jsonObject using down casting
		JSONObject map = (JSONObject) obj;
		//step 3: get the value from json using key
		System.out.println(map.get("url"));
		System.out.println(map.get("browser"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		System.out.println(map.get("timeout"));
	}

}
