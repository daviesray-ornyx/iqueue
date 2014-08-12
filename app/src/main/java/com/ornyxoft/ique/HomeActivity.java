package com.ornyxoft.ique;

import com.microsoft.windowsazure.mobileservices.*;
import com.ornyxoft.ique.Models.IQTest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class HomeActivity extends Activity {

    public MobileServiceClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Get user settings file
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(getString(R.string.USER_ACCESS_MODE_KEY),"Registered");
        //editor.commit();

        try{
            mClient = new MobileServiceClient("https://iqueue.azure-mobile.net/",
                    "AHotqGSYDboBnthlGdpAvTiqMQTXzb38"
                    ,this);
            authenticate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.action_profile:
                // request to show user profile
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.action_take_test:
                // request to take test
                Intent testingIntent = new Intent(this, TestingActivity.class);
                startActivity(testingIntent);
                break;
            case R.id.action_register:
                // request to show user profile
                Intent registrationIntent = new Intent(this, RegistrationActivity.class);
                startActivity(registrationIntent);
                break;
            case R.id.action_login:
                // request to show user profile
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
        Custom methods
     */

    private void createIQTest(){
        try {
            IQTest iqTest = new IQTest();
            iqTest.scenario = "This is the very first testing of login via facebook!";
            iqTest.question = "Which test is this?";
            iqTest.week = 33;
            mClient.getTable(IQTest.class).insert(iqTest, new TableOperationCallback<IQTest>() {
                public void onCompleted(IQTest entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        //
                        System.out.println("IQTest inserted successfully");
                    } else {
                        // Insert failed
                        exception.printStackTrace();
                        System.out.println("ERROR: Failed to insert application");
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void authenticate() {

        // check for user mode
        SharedPreferences prefs = getSharedPreferences(getString(R.string.USER_SETTINGS_FILE), this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if (!loadUserTokenCache(mClient))
        {
            // If not token is loaded, leave user at home page...
                // Set mode to anonymous
                    // check if anonymous details are ser
            String userId = prefs.getString("uid", "undefined");
            if(userId == "undefined")
            {
                // No user data yet. Set mode to anonymous, username, testslist to an empty list
                editor.putString("uid","-1");
                editor.putString("mode","anonymous");
                editor.commit();
            }
            else{
                // User details available, load them and take user to profile
                System.out.println("Anonymous User is details available..... Show user profile");
                createIQTest();
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
            }

        }
        else {
            // User token is loaded, Take user to profile page
                // Load user profile
            System.out.println("User is registered..... Show user profile");
            createIQTest();
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        }


    }

    private boolean loadUserTokenCache(MobileServiceClient client)
    {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.USER_SETTINGS_FILE), this.MODE_PRIVATE);
        String userId = prefs.getString("uid", "undefined");
        if (userId == "undefined")
            return false;
        String token = prefs.getString("tkn", "undefined");
        if (token == "undefined")
            return false;

        MobileServiceUser user = new MobileServiceUser(userId);
        user.setAuthenticationToken(token);
        client.setCurrentUser(user);

        return true;
    }

    public void showTestView(View view){
        Intent intent = new Intent(this, TestingActivity.class);
        startActivity(intent);
    }
}
