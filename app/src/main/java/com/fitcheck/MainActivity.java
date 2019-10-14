package com.fitcheck;

import android.os.Bundle;

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
}

