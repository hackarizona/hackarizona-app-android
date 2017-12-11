package com.example.hackaz.events;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hackaz.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.example.hackaz.DownloadTask;

import static com.example.hackaz.R.layout.popup;

public class EventDayActivity extends AppCompatActivity {

    private String url;
    private String day;
    private ArrayList<String> eventList;
    private ArrayList<Event> eventObjectData;
    private ListView scheduleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_day);

        eventObjectData = new ArrayList<Event>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
            day = extras.getString("day");


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
        eventList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(result);

            String dayInfo = jsonObject.getString(day);

            JSONArray events = new JSONArray(dayInfo);

            //add each events info to the schedule
            for (int i = 0; i < events.length(); i++){
                JSONObject event = events.getJSONObject(i);

                //accounting for the different formats of json files
                if(url.equals("http://hackarizona.org/activities.json")){

                    eventList.add("\n"  + event.getString("activity") + " "
                            +"\n" +
                            event.getString("time") + " - "
                            + event.getString("location") + "\n" );

                }
                else if(url.equals("http://hackarizona.org/firstbyte.json")){

                    Event currEvent = new Event(event.getString("workshop"),
                            "",event.getString("time"),
                            event.getString("location"), event.getString("description"));

                    //name, time, location
                    eventList.add("\n" + currEvent.getName()+ " "
                            +"\n" + currEvent.getTime() + " - "
                            + currEvent.getLocation() + "\n" );
                    eventObjectData.add(currEvent);

                    //decsription
                    //eventDayData.add("Description: " + currEvent.getDescription() + "\n");

                }
                else if(url.equals("http://hackarizona.org/techtalks.json")){

                    eventList.add("\n" + event.getString("sponsor") + " "
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
                this, android.R.layout.simple_list_item_1, eventList){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                return view;
            }
        };
        scheduleView.setAdapter(adapter);

        scheduleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i("description", eventObjectData.get(position).getDescription());

                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(popup, null);



                Log.i("content", popupView.getContext().toString()+"");

                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView = (TextView) popupWindow.getContentView().
                        findViewById(R.id.popupTextView);
                textView.setText("Description: \n" + eventObjectData.get(position).getDescription());
                textView.setTextSize(15);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(scheduleView, 150, -1200);

            }});

    }
}

