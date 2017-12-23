package com.example.hackaz.schedule;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hackaz.DownloadTask;
import com.example.hackaz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.hackaz.R.layout.popup;

public class ScheduleDayActivity extends AppCompatActivity {
    private String day;
    private ArrayList<String> scheduleList;
    ListView scheduleView;
    private ArrayList<ScheduleEvent> scheduleObjectData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_day);
        scheduleObjectData = new ArrayList<ScheduleEvent>();


        //preparing data for sub activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            day = extras.getString("day");
            //The key argument here must match that used in the other activity
        }


        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://hackarizona.org/masterschedule.json").get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException f) {
            f.printStackTrace();
        }

        addJSONContent(result);
        setUpListView();

    }

    private void addJSONContent(String result) {
        scheduleList = new ArrayList<>();
        int unicode = 0x1F61C;

        try {
            JSONObject jsonObject = new JSONObject(result);

            String dayInfo = jsonObject.getString(day);

            JSONArray events = new JSONArray(dayInfo);

            //add each events info to the schedule
            for (int i = 0; i < events.length(); i++){
                JSONObject event = events.getJSONObject(i);

                String eventName = event.getString("eventtitle");
                String eventType = event.getString("eventtype");
                String time = event.getString("time");
                String location = event.getString("location");
                String description = event.getString("description");

                //5 event types
                /*
                 * required: saguaro 0x1F335
                 * activity: running 0x1F3C3
                 * techtalk: light 0x1F4A1
                 * firstbyte: computer 0x1F4BB
                 * food: taco 0x1F32E
                 */

                if(eventType.equals("required"))
                    unicode = 0x1F335;
                else if (eventType.equals("activity"))
                    unicode = 0x1F3C3;
                else if (eventType.equals("techtalk"))
                    unicode = 0x1F4A1;
                else if (eventType.equals("firstbyte"))
                    unicode = 0x1F4BB;
                else
                    unicode = 0x1F32E;

                scheduleList.add(getEmojiByUnicode(unicode) + " " + eventName + " " + "\n" +
                        time + " - " + location + "\n" );
                ScheduleEvent currEvent = new ScheduleEvent(eventName, eventType, time, location, description);
                scheduleObjectData.add(currEvent);

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    //adds the list view and json listview content
    private void setUpListView() {

        // Get ListView object from xml
        scheduleView = (ListView) findViewById(R.id.schedule_day_list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, scheduleList){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                return view;
            }
        };

        //sets up listener for description popup
        scheduleView.setAdapter(adapter);
        scheduleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(popup, null);

                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView = (TextView) popupWindow.getContentView().
                        findViewById(R.id.popupTextView);
                textView.setText("Description: \n" + scheduleObjectData.get(position).getDescription());
                textView.setTextSize(15);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(scheduleView, 150, -1200);

            }});

    }


    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
