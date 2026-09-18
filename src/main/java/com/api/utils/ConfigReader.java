package com.api.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	
	private static Properties properties;
	
	public static void loadProperties() {
		
		properties = new Properties();
		
		try(InputStream file = ConfigReader.class
				.getClassLoader()
				.getResourceAsStream("config.properties")){
			
		if(file==null) {
			throw new RuntimeException(
					"config.properties file not found in src/main/resources"
					);
		}	
			
		properties.load(file);
		
		}catch(Exception e) {
			throw new RuntimeException("failed to load config.properties",e);
		}
		
		
	}

	
	public static String getBaseUrl() {
		return properties.getProperty("base.url");
	}
	
	public static long getResponseTimeout() {
		return Long.parseLong(properties.getProperty("response.timeout"));
		
		
	}
	
}
