package com.hackarizona.hackaz;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Emily the Destroyer on 12/5/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {
    private String out;
    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection;

        try {

            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;
                result += current;
                data = reader.read();

            }
            out = result;
            return result;

        } catch (MalformedURLException d) {
            d.printStackTrace();
            return "Failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    protected String result(){
        return out;
    }
}
