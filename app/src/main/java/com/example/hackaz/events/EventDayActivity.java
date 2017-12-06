package com.example.hackaz.events;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.example.hackaz.DownloadTask;

public class EventDayActivity extends AppCompatActivity {

    private String url;
    private String day;
    private ArrayList<String> eventDayData;
    private ListView scheduleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_day);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
            day = extras.getString("day");
            //The key argument here must match that used in the other activity
            Log.i("url", url);
            Log.i("day", day);

        }
        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute(url).get();
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
        eventDayData = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(result);

            String dayInfo = jsonObject.getString(day);

            JSONArray events = new JSONArray(dayInfo);

            //add each events info to the schedule
            for (int i = 0; i < events.length(); i++){
                JSONObject event = events.getJSONObject(i);

                //accounting for the different formats of json files
                if(url.equals("http://hackarizona.org/activities.json")){
                    eventDayData.add("\n" + event.getString("activity") + " "
                            +"\n" +
                            event.getString("time") + " - "
                            + event.getString("location") + "\n" );
                }
                else if(url.equals("http://hackarizona.org/firstbyte.json")){
                    eventDayData.add("\n" + event.getString("workshop") + " "
                            +"\n" +
                            event.getString("time") + " - "
                            + event.getString("location") + "\n" );
                }
                else{
                    eventDayData.add("\n" + event.getString("sponsor") + " "
                            + event.getString("talk") + "\n" +
                            event.getString("time") + " - "
                            + event.getString("location") + "\n" );
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    //adds the list view and json listview content
    private void setUpListView() {

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.event_day_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, eventDayData){

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
