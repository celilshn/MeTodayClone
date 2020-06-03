package com.cengcelil.metodayclone;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Aktivite Başladı. Görünümler Tanımlanıyor..");
        viewSettings();
        Log.d(TAG, "onCreate: Görünümler Tanımlandı. NavigationView Ayarlanıyor..");
        navigationViewSettings();
        Log.d(TAG, "onCreate: NavigationView Ayarlandı.");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    private void navigationViewSettings() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.getHeaderView(0).setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void viewSettings() {
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navigation_header: {
                drawerLayout.closeDrawer(GravityCompat.START);
                Log.d(TAG, "onClick: Header Öğesine Tıklandı");
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.images_today)
            Log.d(TAG, "onNavigationItemSelected: Gecmiste Bugün seçeneğine tıklandı.");

        return true;
    }
}
