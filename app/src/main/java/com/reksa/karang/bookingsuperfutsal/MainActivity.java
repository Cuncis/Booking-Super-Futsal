package com.reksa.karang.bookingsuperfutsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    CardView cardProfil, cardDaftarLokasi, cardLokasiTerdekat, cardTagihan;
    TextView textNameOfPerson, textDrawerUsername, textDrawerEmail;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //for change text or image from navigationView
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        textNameOfPerson = findViewById(R.id.text_nameOfPerson);
        textDrawerUsername = headerView.findViewById(R.id.text_username);
        textDrawerEmail = headerView.findViewById(R.id.text_email);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cardProfil = findViewById(R.id.card_profil);
        cardDaftarLokasi = findViewById(R.id.card_list_location);
        cardLokasiTerdekat = findViewById(R.id.card_closest_location);
        cardTagihan = findViewById(R.id.card_bill);

        cardProfil.setOnClickListener(this);
        cardDaftarLokasi.setOnClickListener(this);
        cardLokasiTerdekat.setOnClickListener(this);
        cardTagihan.setOnClickListener(this);

        textNameOfPerson.setText(getIntent().getStringExtra("username"));
        textDrawerUsername.setText(getIntent().getStringExtra("username"));
        textDrawerEmail.setText(getIntent().getStringExtra("email"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_profil:
                startActivity(new Intent(MainActivity.this, ProfilActivity.class));
                break;
            case R.id.card_list_location:
                startActivity(new Intent(MainActivity.this, DaftarLokasiActivity.class));
                break;
            case R.id.card_closest_location:
                startActivity(new Intent(MainActivity.this, LokasiTerdekatActivity.class));
                break;
            case R.id.card_bill:
                startActivity(new Intent(MainActivity.this, TagihanActivity.class));
                break;
            default:
                break;
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
        if (id == R.id.action_logout) {
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Action setting clicked :)", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
