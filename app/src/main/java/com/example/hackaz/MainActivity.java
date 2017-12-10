package com.example.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

    ImageButton scheduleButton;
    ImageButton socialMediaButton;
    ImageButton mapButton;
    ImageButton eventsButton;
    ImageButton mentorHubButton;
    ImageButton livestreamButton;


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

        // map buttons to the buttons in XML
        scheduleButton = (ImageButton) findViewById(R.id.imageButton);
        livestreamButton = (ImageButton) findViewById(R.id.imageButton2);
        mapButton = (ImageButton) findViewById(R.id.imageButton3);
        mentorHubButton = (ImageButton) findViewById(R.id.imageButton4);
        eventsButton = (ImageButton) findViewById(R.id.imageButton5);
        socialMediaButton = (ImageButton) findViewById(R.id.imageButton6);


        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 0;
                navSubPage();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 1;
                navSubPage();
            }
        });

        livestreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 2;
                navSubPage();
            }
        });

        socialMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 3;
                navSubPage();
            }
        });

        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 4;
                navSubPage();
            }
        });

        mentorHubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePos = 5;
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