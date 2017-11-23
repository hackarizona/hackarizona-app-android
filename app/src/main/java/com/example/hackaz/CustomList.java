package com.example.hackaz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joe Kimbuende on 11/19/2017.
 *
 * This class will extend the ArrayAdapter class and basically act as the same
 * only the hope with this is that images AND text will be able to appear for
 * each of the items on the list instead of only text.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] events; // list of events for a specific day
    private final Integer[] imageId; // list of image id's for the dots

    public CustomList(Activity context, String[] events, Integer[] imageId) {
        super(context, android.R.layout.simple_list_item_1, events);
        this.context = context;
        this.events = events;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(android.R.layout.simple_list_item_1, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(android.R.id.text1);
        //ImageView imageView = (ImageView) rowView.findViewById(android.R.id.imageView5);
        txtTitle.setText(events[position]);

        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
