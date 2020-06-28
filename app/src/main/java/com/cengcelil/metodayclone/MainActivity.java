package com.cengcelil.metodayclone;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cengcelil.metodayclone.Model.GalleryHelper;
import com.google.android.material.navigation.NavigationView;

import static com.cengcelil.metodayclone.Utils.READ_PERMISSION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private GalleryHelper galleryHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Aktivite Başladı. Görünümler Tanımlanıyor..");
        viewSettings();
        Log.d(TAG, "onCreate: Görünümler Tanımlandı. NavigationView Ayarlanıyor..");
        navigationViewSettings();
        Log.d(TAG, "onCreate: NavigationView Ayarlandı. GalleryHelper oluşturulacak..");
        galleryHelper = new GalleryHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Uygulama izni kontrol edilecek..");
        controlPermissions();
    }

    private void controlPermissions() {
        if (ContextCompat.checkSelfPermission(this, READ_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "controlPermissions: Uygulama izni zaten alınmış.");
            //Uygulama akışının devamı
            Log.d(TAG, "controlPermissions: "+galleryHelper.getMatchedImages().get(0).getDate());
            Log.d(TAG, "controlPermissions: "+galleryHelper.getMatchedImages().get(0).getTime());


        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_PERMISSION)) {
            Log.d(TAG, "controlPermissions: İzin gerekçesi gösteriliyor..");
            Utils.showDialogPermission(this);
        } else {
            Log.d(TAG, "controlPermissions: Uygulama izni yok. Kullanıcıdan izin alınacak.");
            ActivityCompat.requestPermissions(this, new String[]{READ_PERMISSION}, 1);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==1)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Log.d(TAG, "onRequestPermissionsResult: Kullanıcı izni verdi.");
                controlPermissions();

            }
            else{
                Log.d(TAG, "onRequestPermissionsResult: Kullanıcı izni vermedi. Tekrar istenecek.");
                Utils.showDialogPermission(this);
            }
        }
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
        if (getSupportActionBar() != null)
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
        if (item.getItemId() == R.id.images_today)
            Log.d(TAG, "onNavigationItemSelected: Gecmiste Bugün seçeneğine tıklandı.");

        return true;
    }
}
