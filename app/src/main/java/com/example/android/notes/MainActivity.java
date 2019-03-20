package com.example.android.notes;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity  {


        private DrawerLayout mDrawerLayout;
        Toolbar toolbar; //ha
        ActionBarDrawerToggle actionBarDrawerToggle; //ha


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); //ha
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);




        // navigationView.setNavigationItemS  electedListener(this);


        NavigationView navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
@Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
         int id = menuItem.getItemId();
         Fragment fragment = null;
         Bundle bundle = new Bundle();
         if (id == R.id.nav_breakfastActivity){
             android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             ft.replace(R.id.content_frame, new breakfastActivity());

             ft.commit();
         } else if (id == R.id.nav_lunchActivity) {
             android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             ft.replace(R.id.content_frame, new lunchActivity());
             ft.commit();
         } else if (id == R.id.nav_dinnerActivity) {
             android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             ft.replace(R.id.content_frame, new dinnerActivity());
             ft.commit();
         }

         return true;
                   }
                });
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                    }
                }

        );


    }


    public void setActionBarTitle(String title) {
           getSupportActionBar().setTitle(title);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       // Fragment fragment = null;
         // Bundle bundle = new Bundle();

        return super.onOptionsItemSelected(item);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
       // getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }


    @Override

    public void onBackPressed(){

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }

    }


        @Override

    public void onPostCreate(Bundle savedInstanceState) {

super.onPostCreate(savedInstanceState);
actionBarDrawerToggle.syncState();

        }



}
