package com.example.hackaz.schedule.days;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.hackaz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class SundayActivity extends Activity {

    private ArrayList<String> daySchedule;
    ListView scheduleView;

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }
                return result;

            } catch(MalformedURLException d){
                d.printStackTrace();
                return "Failed";
            } catch(Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunday);

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://hackarizona.org/masterschedule.json").get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException f) {
            f.printStackTrace();
        }
        addJSONContent(result);
        setUpListView();

    }

    private void addJSONContent(String result) {
        daySchedule = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(result);

            String sundayInfo = jsonObject.getString("sunday");

            JSONArray events = new JSONArray(sundayInfo);

            //can loop through array
            for (int i = 0; i < events.length(); i++){

                JSONObject event = events.getJSONObject(i);

                daySchedule.add("\n" + event.getString("eventtitle") + " " + event.getString("subtitle") + "\n" +
                        event.getString("time") + " - " + event.getString("location") + "\n" );
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    //adds the list view and json listview content
    private void setUpListView() {

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.sunday_list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, daySchedule){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                return view;
            }
        };
        scheduleView.setAdapter(adapter);
    }
}