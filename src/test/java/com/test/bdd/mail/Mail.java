package com.test.bdd.mail;

import com.test.bdd.utils.Utils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mail {

    private static final String ADDRESS = "{address}";
    private static final String MESSAGE_ID = "{message_id}";

    private static final String RESOURCE_LIST_ADDRESSES = "";
    private static final String RESOURCE_DELETE_ADDRESSES = "/" + ADDRESS;
    private static final String RESOURCE_LIST_MESSAGES = "/" + ADDRESS + "/messages";
    private static final String RESOURCE_GET_MESSAGE_JSON = "/" + ADDRESS + "/messages/" + MESSAGE_ID;
    private static final String RESOURCE_GET_MESSAGE_HTML = "/" + ADDRESS + "/messages/" + MESSAGE_ID + "/html";
    private static final String RESOURCE_GET_MESSAGE_TXT = "/" + ADDRESS + "/messages/" + MESSAGE_ID + "/text";
    private static final String RESOURCE_DELETE_MESSAGES = "/" + ADDRESS + "/messages";
    private static final String RESOURCE_DELETE_MESSAGE = "/" + ADDRESS + "/messages/" + MESSAGE_ID;

    private static final String RESPONSE_RESULTS = "results";
    private static final String RESPONSE_TEXT = "text";

    public static String getLastMessageLink(String email) {

        String text = "";
        String resource = RESOURCE_LIST_MESSAGES;

        resource = resource.replace(ADDRESS, email);

        HttpGet request = (HttpGet) RequestHandler.setRequest(
                RequestHandler.GET,
                resource + "?limit=1&before=" + Utils.getCurrentDateISO()
        );

        text = getEmailText(ResponseHandler.getResponse(request));

        return Utils.getFirstURLFromString(text);
    }

    public static String deleteMessage(String email) {
        String resource = RESOURCE_DELETE_MESSAGE;

        resource = resource.replace(ADDRESS, email).replace(MESSAGE_ID, "PUT_EMAIL_ID");

        HttpDelete request = (HttpDelete) RequestHandler.setRequest(
                RequestHandler.DELETE,
                resource
        );

        return ResponseHandler.getResponse(request);
    }

    public static String deleteAllMessages(String email) {
        String resource = RESOURCE_DELETE_MESSAGES;

        resource = resource.replace(ADDRESS, email);

        HttpDelete request = (HttpDelete) RequestHandler.setRequest(
                RequestHandler.DELETE,
                resource
        );

        return ResponseHandler.getResponse(request);
    }

    private static String getEmailText(String body) {

        String result = "";
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(new JSONObject(body).get(RESPONSE_RESULTS).toString());
            result = String.valueOf(jsonArray.getJSONObject(0).get(RESPONSE_TEXT));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
