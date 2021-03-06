package com.hackarizona.hackaz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        super(context, R.layout.list_single, events);
        this.context = context;
        this.events = events;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        // TODO: Make sure to put an image here for
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(events[position]);

        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
