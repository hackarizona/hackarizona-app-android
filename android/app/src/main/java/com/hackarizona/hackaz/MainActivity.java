package com.hackarizona.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hackarizona.hackaz.events.EventsActivity;
import com.hackarizona.hackaz.map.MapActivity;
import com.hackarizona.hackaz.schedule.ScheduleActivity;

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
    private String livestreamLink;
    private String mentorHubLink;


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
        scheduleButton = (ImageButton) findViewById(R.id.scheduleButton);
        livestreamButton = (ImageButton) findViewById(R.id.livestreamButton);
        mapButton = (ImageButton) findViewById(R.id.mapButton);
        mentorHubButton = (ImageButton) findViewById(R.id.mentorHubButton);
        eventsButton = (ImageButton) findViewById(R.id.eventsButton);
        socialMediaButton = (ImageButton) findViewById(R.id.socialMediaButton);


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


        addJSONContent("livestream","http://hackarizona.org/livestream.json");
        addJSONContent("mentorhub","http://hackarizona.org/mentorhub.json");
    }

    private void addJSONContent(String name, String url) {
        //set up the byte stream to receive the JSON
        DownloadTask task = new DownloadTask();
        String resultLivestream = null;

        try {
            resultLivestream = task.execute(url).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException f) {
            f.printStackTrace();
        }
        try {
            JSONObject jsonObject_ls = new JSONObject(resultLivestream);
            String urlInfo_ls = jsonObject_ls.getString(name);
            JSONArray links_ls = new JSONArray(urlInfo_ls);

            //get the link
            JSONObject link = links_ls.getJSONObject(0);
            if(name.equals("livestream"))
                livestreamLink = link.getString("link");
            else
                mentorHubLink = link.getString("link");

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
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mentorHubLink));
            startActivity(intent);
        }
    }
}