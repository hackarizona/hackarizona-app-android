package com.hackarizona.hackaz.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hackarizona.hackaz.R;
import com.hackarizona.hackaz.TouchImageView;

public class FloorActivity extends AppCompatActivity {
    private int floorNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            floorNum = extras.getInt("floor");
        }

        TouchImageView imageView = (TouchImageView) findViewById(R.id.floorImageView);

        if(floorNum == 0){
            imageView.setImageResource(R.drawable.scieng_1);
        }
        else if(floorNum == 1){
            imageView.setImageResource(R.drawable.scieng_2);
        }
        else if(floorNum == 2){
            imageView.setImageResource(R.drawable.scieng_3);
        }
        else if(floorNum == 3){
            imageView.setImageResource(R.drawable.scieng_4);
        }
        else{
            imageView.setImageResource(R.drawable.scieng_5);
        }


    }
}
