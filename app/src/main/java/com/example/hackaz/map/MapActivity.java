package com.example.hackaz.map;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hackaz.R;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    private ArrayList<String> floors;
    ListView mapView;
    private int savePos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        savePos = 0;
        floors = new ArrayList<>();
        floors.add("Level 1");
        floors.add("Level 2");
        floors.add("Level 3");
        floors.add("Level 4");
        floors.add("Level 5");

        // Get ListView object from xml
        mapView = (ListView) findViewById(R.id.floorList);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, floors){

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
        mapView.setAdapter(adapter);

        mapView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savePos = position;
                navSubPage();
            }
        });
    }

    private void navSubPage(){
        Intent intent = new Intent(this, FloorActivity.class);
        intent.putExtra("floor", savePos);
        startActivity(intent);
    }
}
