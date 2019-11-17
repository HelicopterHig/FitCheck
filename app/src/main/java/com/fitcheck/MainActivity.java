package com.fitcheck;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.fitcheck.ui.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private LoginFragment mLoginFragment;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            loadFragment();
        }

    }

    private void loadFragment() {

        if (mLoginFragment == null) {

            mLoginFragment = new LoginFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.fragmentFrame, mLoginFragment, LoginFragment.TAG).commit();
    }
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.class.getName(), "onResume()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.class.getName(), "onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.class.getName(), "onPause()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.class.getName(), "onRestart()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.class.getName(), "onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getName(), "onDestroy()");
    }
}

