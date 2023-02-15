package com.test.bdd.setup.properties;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Property manager reads a json file and returns the value for them.
 * <p>
 * When the client code asks for the value of key, first, the class looks for a defined environment variable. If exists
 * then it takes the value defined in the environment variable. If not, then the manager checks if the key is defined
 * in the json file and returns the value.
 */
public class PropertyManager {
    /**
     * Stores the map of the parsed json text.
     */
    private static JSONObject jsonObject = null;

    /**
     * Where the properties file is stored
     */
    private static final String PROPERTIES_FILE = "src/test/resources/properties/properties.json";
    private static final String PROFILE = "profile";

    /**
     * Get the propertyName of the property. First, search if the property is defined as environmet variable. If not
     * then it returns the value that is defined in the file.
     *
     * @param propertyName The name of the property for search
     * @return The value, as string, of the property.
     */
    public static String getProperty(String propertyName) {

        JSONParser parser = new JSONParser();
        try {
            if (jsonObject == null) {
                Object obj = parser.parse(new FileReader(PROPERTIES_FILE));
                jsonObject = (JSONObject) obj;
            }
            if (StringUtils.equals(PROFILE, propertyName) && System.getProperty(PROFILE) != null) {
                return System.getProperty(PROFILE);
            }
            return System.getenv().getOrDefault(propertyName, (String) jsonObject.get(propertyName));

        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(e);
        }

    }

}