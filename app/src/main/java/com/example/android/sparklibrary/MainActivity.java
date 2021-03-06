package com.example.android.sparklibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sparklibrary.Fragmenti.ClanoviFragment;
import com.example.android.sparklibrary.Fragmenti.KnjigaUnos;
import com.example.android.sparklibrary.Fragmenti.KnjigeFragment;
import com.example.android.sparklibrary.Fragmenti.PostavkeFragment;
import com.example.android.sparklibrary.Fragmenti.PosudjeneKnjigeFragment;
import com.example.android.sparklibrary.Storage.AppHelper;
import com.example.android.sparklibrary.Storage.PostavkeStorage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    ImageView userSlika;
    TextView ime_knjizare;

    FragmentManager fm = getSupportFragmentManager();

    PostavkeStorage postavkeStorage = new PostavkeStorage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.hamburger);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                fm.beginTransaction().replace(R.id.content_view,new KnjigaUnos()).addToBackStack(new KnjigeFragment().getClass().getName()).commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        AppHelper.getInstance().initAppHelperWithContext(this);
        userSlika = (ImageView)findViewById(R.id.idknjiznica_slika);
        ime_knjizare = (TextView)findViewById(R.id.ime_knjizare);
        //setValuesOnForm();


        if (AppHelper.getInstance().getPostavkeStorage() != null) {
            if (AppHelper.getInstance().getPostavkeStorage().getTema_broj()) {
                Log.d(TAG, "onCreate: " + AppHelper.getInstance().getPostavkeStorage().getTema_broj());
                setTheme(android.R.style.Theme_Holo_Light);
            } else {
                setTheme(android.R.style.Theme_Black);
                Log.d(TAG, "onCreate: " + AppHelper.getInstance().getPostavkeStorage().getTema_broj());
            }
        }
        fm.beginTransaction().replace(R.id.content_view,new  KnjigeFragment()).addToBackStack(new KnjigeFragment().getClass().getName()).commit();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            fm.beginTransaction().replace(R.id.content_view,new  KnjigeFragment()).commit();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        if (id == R.id.nav_knjige) {
            fm.beginTransaction().replace(R.id.content_view,new  KnjigeFragment()).commit();
        } else if (id == R.id.nav_members) {
            fm.beginTransaction().replace(R.id.content_view,new ClanoviFragment()).commit();
        } else if (id == R.id.nav_manage) {
            fm.beginTransaction().replace(R.id.content_view,new PosudjeneKnjigeFragment()).commit();

        } else if (id == R.id.nav_settings) {
            fm.beginTransaction().replace(R.id.content_view,new PostavkeFragment()).commit();
        } else if (id == R.id.nav_logoff) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
