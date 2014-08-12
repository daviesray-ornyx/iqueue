package com.ornyxoft.ique;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.microsoft.windowsazure.mobileservices.*;
import com.ornyxoft.ique.R;

public class LoginActivity extends Activity {

    public MobileServiceClient mClient;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cm = (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loginWithFacebook(View view){
        //check for connectivity
        activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting())
        {
            // go ahead with login process
            mClient.login(MobileServiceAuthenticationProvider.Facebook,
                    new UserAuthenticationCallback() {

                        @Override
                        public void onCompleted(MobileServiceUser user,
                                                Exception exception, ServiceFilterResponse response) {

                            if (exception == null) {
                                cacheUserToken(user);       //cache user details
                                showProfile();
                            } else {
                                System.out.println("ERROR: You must log in. Login Required");
                            }
                        }
                    });
        }
        else{
            // inform user no internet access
        }

    }

    private void cacheUserToken(MobileServiceUser user)
    {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.USER_SETTINGS_FILE), this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("uid", user.getUserId());
        editor.putString("tkn", user.getAuthenticationToken());
        editor.commit();
    }

    private void showProfile()
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
