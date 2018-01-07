package com.hackarizona.hackaz.schedule;

import android.app.ProgressDialog;
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
import com.hackarizona.hackaz.R;


import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private ArrayList<String> daySchedule;
    ListView scheduleView;
    private int savePos;
    ProgressDialog dialog;


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

        //navigate to a subpage
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
        Intent intent = new Intent(this, ScheduleDayActivity.class);

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

        //show a dialog to indicate the page is loading
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dialog.hide();
    }
}
