package com.salmakhd.android.shahabseries.navigationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.salmakhd.android.shahabseries.R;
import com.salmakhd.android.shahabseries.navigationapplication.classes.ContactModel;
import com.salmakhd.android.shahabseries.navigationapplication.classes.Database;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.AddContactFragment;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.HomeFragment;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.SettingsFragment;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button startServiceBtn, stopServiceBtn;
    private ImageView settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        startServiceBtn = findViewById(R.id.start_service_button);
        stopServiceBtn = findViewById(R.id.stop_service_button);
        settingBtn = findViewById(R.id.toolbar_btn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.setting) {
                    SettingsFragment settingsFragment = new SettingsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).addToBackStack(null).commit();
                    Toast.makeText(NavigationActivity.this, "Setting clicked", Toast.LENGTH_LONG).show();
                    menuItem.setCheckable(true);
                    drawerLayout.closeDrawers();
                }
                else if(menuItem.getItemId() == R.id.music) {
                    Toast.makeText(NavigationActivity.this, "Music clicked", Toast.LENGTH_LONG).show();
                    menuItem.setCheckable(true);
                    drawerLayout.closeDrawers();
                }
                else if(menuItem.getItemId() == R.id.profile) {
                    Toast.makeText(NavigationActivity.this, "Profile clicked", Toast.LENGTH_LONG).show();
                    menuItem.setCheckable(true);
                    drawerLayout.closeDrawers();
                }
                else if(menuItem.getItemId()==R.id.add_contact) {
                    menuItem.setCheckable(true);
                    AddContactFragment addContactFragment = new AddContactFragment();
                    drawerLayout.closeDrawers();
                    getSupportFragmentManager().beginTransaction().add(R.id.container, addContactFragment).addToBackStack(null).commit();
                } else if(menuItem.getItemId() == R.id.calender) {
                    Toast.makeText(NavigationActivity.this, "Calender clicked", Toast.LENGTH_LONG).show();
                    menuItem.setCheckable(true);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, SenfiService.class);
                startService(intent);
            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, SenfiService.class);
                stopService(intent);
            }
        });

        // add fragment to frame layout in main layout
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();
        Database db = new Database(this);
        ArrayList<ContactModel> list = db.getContacts();
        for(int i =0 ; i< list.size(); i++) {
            Log.i("MyFirstLog", list.get(i).toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
        }
        return true;
    }
}