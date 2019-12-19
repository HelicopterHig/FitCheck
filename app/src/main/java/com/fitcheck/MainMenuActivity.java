package com.fitcheck;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fitcheck.ui.map.MapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    float x1,y1;
    float x2,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home_main, R.id.navigation_statistic, R.id.navigation_calendar, R.id.navigation_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_map, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item_one) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
            // Do something
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
// when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
//if left to right sweep event on screen
                if (x1 < x2)
                {
                    //Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();

                }
// if right to left sweep event on screen
                if (x1 > x2)
                {
                    //  Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    //Intent i = new Intent(MainMenuActivity.this,QrCodeActivity.class);
                    //startActivity(i);

                }
// if UP to Down sweep event on screen
                if (y1 < y2)
                {
//Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }
//if Down to UP sweep event on screen
                if (y1 > y2)
                {
// look this dude
                }
                break;
            }
        }
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainMenuActivity.class.getName(), "onResume()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainMenuActivity.class.getName(), "onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainMenuActivity.class.getName(), "onPause()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainMenuActivity.class.getName(), "onRestart()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainMenuActivity.class.getName(), "onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainMenuActivity.class.getName(), "onDestroy()");
    }

}

