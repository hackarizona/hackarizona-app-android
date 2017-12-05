package com.example.hackaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SocialMediaActivity extends Activity {
    ArrayList<String> pages;
    ListView listView;
    private int savePos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        savePos = 0;
        pages = new ArrayList<>();
        pages.add("Facebook");
        pages.add("Instagram");
        pages.add("Twitter");
        pages.add("Snapchat");

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.socialMediaList);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, pages){

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
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info", position+"");
                savePos = position;
                navSubPage();
            }
        });
    }

    private void navSubPage() {
        if (savePos == 0) {
            //Facebook
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hackarizona"));
            startActivity(intent);
        } else if (savePos == 1) {
            //Twitter
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hack_arizona"));
            startActivity(intent);
        } else if (savePos == 2) {
            //Instagram
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hackarizona/"));
            startActivity(intent);
        } else if (savePos == 3) {
            //TODO Snapchat
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snapchat.com"));
            startActivity(intent);
        }
    }
}

