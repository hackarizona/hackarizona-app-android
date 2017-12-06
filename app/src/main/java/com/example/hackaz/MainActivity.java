package com.example.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.events.EventsActivity;
import com.example.hackaz.map.MapActivity;
import com.example.hackaz.mentorhub.MentorActivity;
import com.example.hackaz.schedule.ScheduleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    ArrayList<String> pages;
    ListView listView;
    private int savePos;
    private ArrayList<String> dataJSON;
    private String livestreamLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savePos = 0;
        pages = new ArrayList<>();
        pages.add("Schedule");
        pages.add("Map");
        pages.add("Live Stream");
        pages.add("Social Media");
        pages.add("Events");
        pages.add("Mentor Hub");

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, pages){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF TEXT COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(20);
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savePos = position;
                navSubPage();
            }
        });


        addJSONContent();
    }

    private void addJSONContent() {
        //set up the byte stream to receive the JSON
        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://hackarizona.org/livestream.json").get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException f) {
            f.printStackTrace();
        }
        dataJSON = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(result);
            String urlInfo = jsonObject.getString("livestream");
            JSONArray links = new JSONArray(urlInfo);

            //get the link
            JSONObject link = links.getJSONObject(0);
            livestreamLink = link.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void navSubPage(){
        if(savePos == 0){
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
        }
        else if(savePos == 1){
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
        else if(savePos == 2){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(livestreamLink));
            startActivity(intent);
        }
        else if(savePos == 3){
            Intent intent = new Intent(this, SocialMediaActivity.class);
            startActivity(intent);
        }
        else if(savePos == 4){
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        else if(savePos == 5){
            //TODO
            Intent intent = new Intent(this, MentorActivity.class);
            startActivity(intent);
        }
    }
}