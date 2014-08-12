package com.ornyxoft.ique;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TestListAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> itemsArrayList;

    public TestListAdapter(Context context, ArrayList<Item> itemsArrayList) {

        super(context, R.layout.listview_test_list_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.listview_test_list_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.test_item_icon);

        // 4. Set the text for textView
        //Drawable imageDrawable = itemsArrayList.get(position).getCorrectStatus() ? R.id.
        if(itemsArrayList.get(position).getCorrectStatus())
        {
            imageView.setImageResource(R.drawable.ic_test_item_correct);
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_test_item_wrong);
        }
        labelView.setText(itemsArrayList.get(position).getTitle());
        valueView.setText(itemsArrayList.get(position).getDescription());

        // 5. retrn rowView
        return rowView;
    }
}