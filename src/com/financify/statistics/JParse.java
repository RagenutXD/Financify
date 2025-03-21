package com.financify.statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JParse {
	
	private JSONObject jsonObject;

	public JParse(String file_dir) {
		String jsonString = getJSONFromFile(file_dir);
		
		try {
			
			JSONParser parser = new JSONParser();
			Object object = parser.parse(jsonString);
			jsonObject = (JSONObject) object;
			
		} catch (Exception e) {
			
		}
	}
	
	
	private String getJSONFromFile(String file_dir) {
		String jsonText= "";
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file_dir);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				jsonText += line +"\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonText;
		
	}
	
	public String getStringFromKey(String key) {
		return (String) jsonObject.get(key);
	}
	
	public JSONObject getObjectFromKey(String key) {
		return (JSONObject) jsonObject.get(key);
	}
	
	public int getIntFromKey(String key) {
		return (int) jsonObject.get(key);
	}
	
	public double getDoubleFromKey(String key) {
		return (double) jsonObject.get(key);
	}
	
	public JSONArray getArrayFromKey(String key) {
		return (JSONArray) jsonObject.get(key);
	}
}
