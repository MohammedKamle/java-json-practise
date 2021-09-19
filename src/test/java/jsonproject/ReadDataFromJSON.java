package jsonproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSON {

	public static void main(String[] args) throws IOException, ParseException {
		// Below class comes from external library json.simple from Google
		JSONParser jsonparser = new JSONParser();
		
		FileReader reader = new FileReader(".\\jsonFiles\\employee.json");
		
		// Parsing the json file
	    Object obj = 	jsonparser.parse(reader);
	    
	    //Converting java object to json object
	    JSONObject empJsonObj = (JSONObject)obj;
	    
	    // getting values from Json file
	    String firstName =   (String) empJsonObj.get("firstName");
	    String lastname  = (String) empJsonObj.get("lastName");
	    System.out.println("First name is "+firstName+" and the last name is "+lastname);
	    System.out.println("/*************************************************/");
	    //getting json array
	    JSONArray array = (JSONArray)empJsonObj.get("address");
	    for (int i = 0; i < array.size(); i++) {
			JSONObject address = (JSONObject)array.get(i);
			String street =(String)address.get("street");
			String state = (String)address.get("state");
			String city = (String)address.get("city");
			
			System.out.println("Address at index "+i+" is ...");
			System.out.println("Street: "+street);
			System.out.println("State: "+state);
			System.out.println("City:"+city);
			System.out.println("/*************************************************/");
		}

	}

}
