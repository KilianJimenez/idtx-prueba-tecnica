package com.test.bdd.mail;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestHandler {

    private static final String BASE_URL = "https://mapil.co/api/v1/email-addresses/";
    private static final String API_TOKEN = "04125436188d88a4a6bcc6f276d0a644";
    private static final String API_SECRET = "25ca136f88f48e099856cbf38f7a7b6a";
    private static final String ACCEPT_NAME_HEADER = "accept";
    private static final String ACCEPT_VALUE_VALUE = "application/json";
    private static final String AUTH_NAME_HEADER = "Authorization";
    private static final String AUTH_VALUE_VALUE = "Basic ";

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";

    public static HttpRequestBase setRequest(String method, String resource) {
        HttpRequestBase request = null;

        switch (method) {
            case GET:
                request = new HttpGet(BASE_URL + resource);
                break;
            case POST:
                request = new HttpPost(BASE_URL + resource);
                break;
            case DELETE:
                request = new HttpDelete(BASE_URL + resource);
                break;
            default:
                throw new IllegalArgumentException("Unhandled HTTP method.");
        }

        setHeaders(request);
        return request;
    }

    private static HttpRequestBase setHeaders(HttpRequestBase request) {
        String encodedCredentials = Base64.getEncoder().encodeToString((API_TOKEN + ":" + API_SECRET).getBytes(StandardCharsets.UTF_8));

        request.setHeader(ACCEPT_NAME_HEADER, ACCEPT_VALUE_VALUE);
        request.setHeader(AUTH_NAME_HEADER, AUTH_VALUE_VALUE + encodedCredentials);

        return request;
    }
}
