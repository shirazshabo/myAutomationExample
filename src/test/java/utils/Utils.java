package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static String readProperty(String key) {
		String value= "";
		
		try (InputStream input = new FileInputStream("./src/test/resources/data/configuration.properties")) {
			// create properties object
			Properties prop = new Properties();
			// load a properties file into this object
			prop.load(input);
			// get the property value and print it out
			value = prop.getProperty(key);
		} catch (Exception e) {

		}

		return value;
	}
}
