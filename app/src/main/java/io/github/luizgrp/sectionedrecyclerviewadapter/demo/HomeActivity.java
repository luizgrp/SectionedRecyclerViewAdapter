package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {

            HomeFragment homeFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment).commit();

            drawer.openDrawer(GravityCompat.START);
        }
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_example1:
                replaceFragment(new Example1Fragment());
                break;
            case R.id.nav_example2:
                replaceFragment(new Example2Fragment());
                break;
            case R.id.nav_example3:
                replaceFragment(new Example3Fragment());
                break;
            case R.id.nav_example4:
                replaceFragment(new Example4Fragment());
                break;
            case R.id.nav_example5:
                replaceFragment(new Example5Fragment());
                break;
            case R.id.nav_example6:
                replaceFragment(new Example6Fragment());
                break;
            case R.id.nav_example7:
                replaceFragment(new Example7Fragment());
                break;
            case R.id.nav_example8:
                replaceFragment(new Example8Fragment());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }
}
