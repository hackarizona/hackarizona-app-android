package com.example.hackaz.events.firstByte;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.hackaz.R;

import java.util.ArrayList;

public class FirstByteActivity extends AppCompatActivity {

    private ArrayList<String> daySchedule;
    ListView scheduleView;
    private int savePos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_byte);

        savePos = 0;
        daySchedule = new ArrayList<>();
        daySchedule.add("Friday");
        daySchedule.add("Saturday");
        daySchedule.add("Sunday");

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.firstByteList);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, daySchedule){

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
        if(savePos == 0){
            Intent intent = new Intent(this, Fri2Activity.class);
            startActivity(intent);
        }
        else if(savePos == 1){
            Intent intent = new Intent(this, Sat2Activity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, Sun2Activity.class);
            startActivity(intent);
        }

    }
}
