package com.ornyxoft.ique;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ornyxoft.ique.R;

import java.util.ArrayList;

public class ProfileActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // 1. pass context and data to the custom adapter
        TestListAdapter adapter = new TestListAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        ListView listView = (ListView) findViewById(R.id.listView);

        // 3. setListAdapter
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings:
                // request to show settings page
                break;
            case R.id.action_help:
                // request to show help page
                break;
            case R.id.action_take_test:
                // request to take test
                Intent testingIntent = new Intent(this, TestingActivity.class);
                startActivity(testingIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /*
    Custom Methods
     */
    private ArrayList<Item> generateData(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Item 1","jkjkjkjkandroid:layoutkjkjkandroid:layoutkjkjkandroid:layout_weight=\"1\"jsdfsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",true));
        items.add(new Item("Item 2","jkjkjkjkandroid:layoutkjkjkandroid:layoutkjkjkandroid:layout_weight=\"1\"jsdfsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",true));
        items.add(new Item("Item 3","jkjkjkjkandroid:layoutkjkjkandroid:layoutkjkjkandroid:layout_weight=\"1\"jsdfsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",false));
        items.add(new Item("Item 1","First Item on the list",true));
        items.add(new Item("Item 2","Second Item on the list",true));
        items.add(new Item("Item 3","Third Item on the list",true));
        items.add(new Item("Item 1","First Item on the list",false));
        items.add(new Item("Item 2","Second Item on the list",false));
        items.add(new Item("Item 3","Third Item on the list",false));
        items.add(new Item("Item 1","First Item on the list",false));
        items.add(new Item("Item 2","Second Item on the list",false));
        items.add(new Item("Item 3","jkjkjkjkandroid:layoutkjkjkandroid:layoutkjkjkandroid:layout_weight=\"1\"jsdfsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",true));

        return items;
    }
}
