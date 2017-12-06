package com.example.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.map.MapActivity;
import com.example.hackaz.mentorhub.MentorActivity;
import com.example.hackaz.schedule.ScheduleActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<String> pages;
    ListView listView;
    private int savePos;

    // TextView adSponsorText;
    ArrayList<String> sponsors; // list of all sponsors
    int i = 0;
    private String notification_channel;


    // TODO Delete if don't work
    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = new Integer[]{
            R.drawable.blue_dot,
            R.drawable.blue_dot,
            R.drawable.blue_dot,
            R.drawable.blue_dot,
            R.drawable.blue_dot,
            R.drawable.blue_dot,
            R.drawable.blue_dot,

    };


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
        // TODO it's been changed
        //listView = (ListView) findViewById(R.id.list);



        // TODO Uncomment it out later

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, R.layout.list_single, web){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(R.id.txt);

                // Choice of color
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(20);
                return view;
            }
        };
        listView.setAdapter(adapter);
        /*

        CustomList adapter = new
                CustomList(this, web, imageId);
        list=(ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);
        */

        // TODO Uncomment

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
                savePos = position;
                navSubPage();
            }
        });

    // end of onCreate() method
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
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=YXtyd1rkbkI"));
            startActivity(intent);
        }
        else if(savePos == 3){
            //TODO Social Media
        }
        else if(savePos == 4){
            //TODO Events
        }
        else{
            Intent intent = new Intent(this, MentorActivity.class);
            startActivity(intent);
        }
    }
}


