package com.test.bdd.mail;

import com.test.bdd.utils.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ResponseHandler {

    private static final String HTTP_CLIENT = "HttpClient: ";

    public static String getResponse(HttpRequestBase request) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            logResponse(request, response);
            result = getResult(response);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getResult(CloseableHttpResponse response) {

        String result = "";
        HttpEntity entity = response.getEntity();
        try {
            result = EntityUtils.toString(entity);
            entity.getContent().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static void logResponse(HttpRequestBase request, CloseableHttpResponse response) {
        Utils.logger.info(
                HTTP_CLIENT +
                        response.getProtocolVersion() + " " +
                        response.getStatusLine().getStatusCode() + " " +
                        response.getStatusLine().getReasonPhrase() + " " +
                        request.getURI().toString()
        );
    }
}
