package com.nearkingseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nearkingseller.activity.Home_Activity;
import com.nearkingseller.activity.Profile_Activity;
import com.nearkingseller.activity.SignIn_Activity;
import com.nearkingseller.activity.Splash_Screen;
import com.nearkingseller.constants.CommonConstant;

public class Navigation_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context con = Navigation_Activity.this;
    TextView Name,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getSupportActionBar();
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
       // LinearLayout ll_nav_main = (LinearLayout) navigationView.inflateHeaderView(R.layout.nav_header_main);
       // Email = (TextView)navigationView.findViewById(R.id.email);
       // Name = (TextView)navigationView.findViewById(R.id.name);

        final SharedPreferences prefs = getSharedPreferences("nearking_login", MODE_PRIVATE);
        String UserEmail = prefs.getString("user_email", "");
        String UserPass = prefs.getString("user_pass", "");

        CommonConstant.UserEmail = UserEmail;
        CommonConstant.UserPass = UserPass;
        System.out.println("CommonConstant.UserEmail***"+CommonConstant.UserEmail);
//        Email.setText(CommonConstant.UserEmail.toString());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home_intent = new Intent(Navigation_Activity.this, Home_Activity.class);
            home_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(home_intent);
            // Handle the camera action
        } else if (id == R.id.nav_profile) {
            Intent profile_intent = new Intent(Navigation_Activity.this, Profile_Activity.class);
            profile_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(profile_intent);

        } else if (id == R.id.nav_logout) {
            con.getSharedPreferences("nearking_login", 0).edit().clear().commit();
            Toast.makeText(Navigation_Activity.this, "Logout success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(con, SignIn_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
