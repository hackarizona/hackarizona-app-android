package com.hackarizona.hackaz.events;

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

public class EventsActivity extends AppCompatActivity {

    private ArrayList<String> daySchedule;
    ListView scheduleView;
    private int savePos;
//    private Toolbar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
//        topBar = (Toolbar) findViewById(R.id.my_toolbar2);
//        setSupportActionBar(topBar);
//        // Set the display title
//        getSupportActionBar().setTitle("EVENTS");

        savePos = 0;
        daySchedule = new ArrayList<>();
        daySchedule.add(getEmojiByUnicode(0x1F3C3)+" Activities");
        daySchedule.add(getEmojiByUnicode(0x1F4A1)+" Tech Talks");
        daySchedule.add(getEmojiByUnicode(0x1F4BB)+" firstByte");
        daySchedule.add(getEmojiByUnicode(0x1F4FA)+" Live Stream");

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.eventsList);

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
        Intent intent = new Intent(this, EventTypeActivity.class);
        if(savePos == 0){
            intent.putExtra("url", "http://hackarizona.org/activities.json");
        } else if(savePos == 1){
            intent.putExtra("url", "http://hackarizona.org/techtalks.json");
        } else if(savePos == 2){
            intent.putExtra("url", "http://hackarizona.org/firstbyte.json");
        } else if(savePos == 3){
            intent.putExtra("url", "http://hackarizona.org/livestreamevents.json");
        }
        startActivity(intent);
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_favorite) {
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which){
//                        case DialogInterface.BUTTON_POSITIVE:
//                            //Go to firstByte website
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://first-byte.org/hackarizona#schedule"));
//                            startActivity(intent);
//                            break;
//
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            //No button clicked
//                            break;
//                    }
//                }
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
//            builder.setMessage("We have a more detailed schedule of firstByte. Would you like to view it?").setPositiveButton("Yes", dialogClickListener)
//                    .setNegativeButton("No", null).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
