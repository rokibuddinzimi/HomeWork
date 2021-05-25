package com.home.work.NetworkManager;

import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {}

    public String makeGetServiceCall(String reqUrl, String requestMethodType) {
        String response = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(reqUrl);
            conn = (HttpURLConnection) url.openConnection();
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethodType);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            // read the response
            //InputStream in = new BufferedInputStream(conn.getInputStream());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //response = convertGetRequestStreamToString(in);
                response = convertPostRequestStreamToString(conn);
            } else {
                handleError(conn.getResponseCode());
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        } finally {
            conn.disconnect();
        }
        return response;
    }

    private void handleError(int responseCode) {
        switch (responseCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                Log.e(TAG, "Data mismatch error");
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                Log.e(TAG, "Check the URL properly error");
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                Log.e(TAG, "Server down");
                break;
            case HttpURLConnection.HTTP_BAD_GATEWAY:
                Log.e(TAG, "Server down, Bad gateway");
                break;
        }
    }

    public String makePostServiceCall(String reqUrl, String requestMethodType) {
        String response = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL("");
            conn = (HttpURLConnection) url.openConnection();
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.addRequestProperty("Authorization", "Token  " + "token");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod(requestMethodType);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            JSONObject credentials = new JSONObject();
            credentials.put("username", "1111111119");
            credentials.put("pin_code", "0000");

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(credentials.toString());
            wr.flush();
            wr.close();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                response = convertPostRequestStreamToString(conn);
                Log.d("", "" + response);
            } else {
                handleError(conn.getResponseCode());
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        } finally {
            conn.disconnect();
        }
        return response;
    }

    private String convertPostRequestStreamToString(HttpURLConnection conn) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
