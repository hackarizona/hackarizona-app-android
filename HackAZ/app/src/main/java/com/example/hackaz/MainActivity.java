package com.example.hackaz;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<String> pages;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pages = new ArrayList<>();
        pages.add("Schedule");
        pages.add("Map");
        pages.add("Mentors");
        pages.add("Livestream");

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pages);
        listView.setAdapter(adapter);
        

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
            }
        });
    }
}


