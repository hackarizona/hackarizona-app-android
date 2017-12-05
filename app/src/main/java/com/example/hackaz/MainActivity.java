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
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.events.EventsActivity;
import com.example.hackaz.map.MapActivity;
import com.example.hackaz.mentorhub.MentorActivity;
import com.example.hackaz.schedule.ScheduleActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<String> pages;
    ListView listView;
    private int savePos;



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

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, pages){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(20);
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
                savePos = position;
                navSubPage();
            }
        });
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
            Intent intent = new Intent(this, SocialMediaActivity.class);
            startActivity(intent);
        }
        else if(savePos == 4){
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        else{
            //TODO
            Intent intent = new Intent(this, MentorActivity.class);
            startActivity(intent);
        }
    }
}