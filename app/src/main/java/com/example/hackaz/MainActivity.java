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
    //TextView adSponsorText;
    //ArrayList<String> sponsors; // list of all sponsors
    int i = 0;
    private String notification_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //RowItem item = new RowItem(@+id/imageView5);


        savePos = 0;
        pages = new ArrayList<>();
        pages.add("Schedule");
        pages.add("Map");
        pages.add("Live Stream");
        pages.add("Social Media");
        pages.add("Events");
        pages.add("Mentor Hub");

        /* May need to add more to this later */
        /*sponsors = new ArrayList<>();
        sponsors.add("IBM");
        sponsors.add("Raytheon");
        sponsors.add("Intuit");*/

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);


        // Get TextView object from xml
       // adSponsorText = (TextView) findViewById(R.id.text);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, pages){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                //adSponsorText.setTextColor(Color.RED);
                textView.setTextSize(20);
               // adSponsorText.setTextSize(60);
                return view;
            }
        };
        listView.setAdapter(adapter);


       /* Thread t = new Thread() {
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

        t.start();*/

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

    /*
    pages.add("Schedule");
        pages.add("Map");
        pages.add("Live Stream");
        pages.add("Social Media");
        pages.add("Events");
        pages.add("Mentor Hub");
     */

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


