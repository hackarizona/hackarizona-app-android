package com.example.hackaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private ArrayList<String> daySchedule;
    ListView scheduleView;
    private int savePos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        savePos = 0;
        daySchedule = new ArrayList<>();
        daySchedule.add("Friday");
        daySchedule.add("Saturday");
        daySchedule.add("Sunday");

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.scheduleList);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, daySchedule);
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
            Intent intent = new Intent(this, FridayActivity.class);
            startActivity(intent);
        }
        else if(savePos == 1){
            Intent intent = new Intent(this, SaturdayActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, SundayActivity.class);
            startActivity(intent);
        }
    }
}
