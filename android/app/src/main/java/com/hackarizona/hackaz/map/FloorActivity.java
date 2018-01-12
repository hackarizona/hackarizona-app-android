package com.hackarizona.hackaz.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hackarizona.hackaz.R;
import com.hackarizona.hackaz.TouchImageView;

public class FloorActivity extends AppCompatActivity {
    private int floorNum;
    private Toolbar topBar;
    private String floorDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);
        floorDetails = "Key:\n";
        topBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(topBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            floorNum = extras.getInt("floor");
        }

        TouchImageView imageView = (TouchImageView) findViewById(R.id.floorImageView);

        if(floorNum == 0){
            imageView.setImageResource(R.drawable.floor1);
            floorDetails += "Yellow: MLH Hardware Lab\nGreen: Elevators\nOrange: Restrooms\nBrown: Stairs";
            // Set the display title
            getSupportActionBar().setTitle("basement".toUpperCase());
        }
        else if(floorNum == 1){
            imageView.setImageResource(R.drawable.floor2);
            floorDetails += "Red: iSpace\nYellow: 200S Activity Room\nBlue: Help Desk/Volunteers\nGreen: Elevators\nOrange: Restrooms\nBrown: Stairs";
            getSupportActionBar().setTitle("main floor".toUpperCase());
        }
        else if(floorNum == 2){
            imageView.setImageResource(R.drawable.floor3);
            floorDetails += "Purple: firstByte Workshops\nGreen: Elevators\nOrange: Restrooms\nBrown: Stairs";
            getSupportActionBar().setTitle("floor 3".toUpperCase());
        }
        else if(floorNum == 3){
            imageView.setImageResource(R.drawable.floor4);
            floorDetails += "Green: Elevators\nOrange: Restrooms\nBrown: Stairs";
            getSupportActionBar().setTitle("raytheon floor".toUpperCase());
        }
        else{
            imageView.setImageResource(R.drawable.floor5);
            floorDetails += "Yellow: Tech Talks\nGreen: Elevators\nOrange: Restrooms\nBrown: Stairs";
            getSupportActionBar().setTitle("jacobs floor".toUpperCase());
        }

//        // Programmically adjust image to fit phone screens
//        imageView.requestLayout();
//        imageView.getLayoutParams().height = Resources.getSystem().getDisplayMetrics().heightPixels + 150;
//        imageView.getLayoutParams().width = Resources.getSystem().getDisplayMetrics().widthPixels - 100;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            displayPopupWindow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayPopupWindow() {
        final PopupWindow popup = new PopupWindow(this);
        View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        popup.setContentView(popupView);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        TextView textView = (TextView) popup.getContentView().
                findViewById(R.id.popupTextView);
        textView.setText(floorDetails);
        textView.setTextSize(15);

        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }});
    }


}
