package com.test.bdd.setup.properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * profiles manager reads a json file and returns the value for them.
 * <p>
 * When the client code asks for the value of key, first, the class looks for a defined environment variable. If exists
 * then it takes the value defined in the environment variable. If not, then the manager checks if the key is defined
 * in the json file and returns the value.
 */
public class ProfileManager {
    /**
     * Stores the map of the parsed json text.
     */

    /**
     * Where the profiles file is stored
     */
    private static final String PROFILES_FILE = "src/test/resources/properties/profiles.json";

    public static String getProfileProperties(String profile, String property) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        JSONArray profile_property = new JSONArray();
        try {
            if (jsonObject == null) {
                jsonObject = (JSONObject) parser.parse(new FileReader(PROFILES_FILE));
                profile_property = (JSONArray) parser.parse(jsonObject.get(profile).toString());
            }
            return ((JSONObject) profile_property.get(0)).get(property).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(e);
        }
    }
}

