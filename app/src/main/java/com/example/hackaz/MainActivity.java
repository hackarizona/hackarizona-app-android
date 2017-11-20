package com.example.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Timer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.hackaz.livestream.LiveStreamActivity;
import com.example.hackaz.map.MapActivity;
import com.example.hackaz.mentors.MentorActivity;
import com.example.hackaz.schedule.ScheduleActivity;

import java.util.ArrayList;
import java.util.TimerTask;

public class MainActivity extends Activity {

    ArrayList<String> pages;
    ListView listView;
    private int savePos;
    TextView adSponsorText;
    ArrayList<String> sponsors; // list of all sponsors
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        savePos = 0;
        pages = new ArrayList<>();
        pages.add("Schedule");
        pages.add("Map");
        pages.add("Mentors");
        pages.add("Livestream");

        /* May need to add more to this later */
        sponsors = new ArrayList<>();
        sponsors.add("IBM");
        sponsors.add("Raytheon");
        sponsors.add("Intuit");

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);


        // Get TextView object from xml
        adSponsorText = (TextView) findViewById(R.id.text);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, pages){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                adSponsorText.setTextColor(Color.RED);
                textView.setTextSize(20);
                adSponsorText.setTextSize(60);
                return view;
            }
        };
        listView.setAdapter(adapter);


        /*
        This thread will run the sponsor ads - for now it will be commented out
        - Joe
         */
        /*
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(4000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                adSponsorText.setText( sponsors.get(i).toString() );

                                i++;

                                if ( i == sponsors.size() )
                                    i = 0;

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        };

        t.start();
        */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
                savePos = position;
                navSubPage();
            }
        });

        /*while ( i != sponsors.size() ) {
            adSponsorText.setText( sponsors.get( i ).toString() );
            i++;
            // resets i back to zero so that list can start over
            if ( i == sponsors.size() )
                i = 0;
            // Delay in 4 seconds between ads
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
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
            Intent intent = new Intent(this, MentorActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, LiveStreamActivity.class);
            startActivity(intent);
        }
    }
}


