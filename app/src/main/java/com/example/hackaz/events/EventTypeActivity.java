package com.example.hackaz.events;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.R;


import java.util.ArrayList;

public class EventTypeActivity extends AppCompatActivity {
    private String url;

    private ArrayList<String> dayOptions;
    ListView scheduleView;
    private int savePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
            //The key argument here must match that used in the other activity
            Log.i("url", url);
        }

        savePos = 0;
        dayOptions = new ArrayList<>();
        dayOptions.add("Friday");
        dayOptions.add("Saturday");
        dayOptions.add("Sunday");

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.event_type_list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, dayOptions){

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
        scheduleView.setAdapter(adapter);

        scheduleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
                savePos = position;
                navSubPage();
            }
        });
    }

    private void navSubPage(){
        Intent intent = new Intent(this, EventDayActivity.class);
        intent.putExtra("url", url);
        if(savePos == 0){
            intent.putExtra("day", "friday");
        }
        else if(savePos == 1){
            intent.putExtra("day", "saturday");
        }
        else{
            intent.putExtra("day", "sunday");
        }
        startActivity(intent);

    }



}
