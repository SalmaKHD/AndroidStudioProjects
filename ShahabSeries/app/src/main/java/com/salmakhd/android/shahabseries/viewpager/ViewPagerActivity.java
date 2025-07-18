package com.salmakhd.android.shahabseries.viewpager;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.salmakhd.android.shahabseries.R;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.AddContactFragment;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.HomeFragment;
import com.salmakhd.android.shahabseries.navigationapplication.fragments.SettingsFragment;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdaptor viewPagerAdaptor;
    private TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_pager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.my_view_pager);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        // create multiple fragments from a single template
        ViewPagerFragment fragment1 = new ViewPagerFragment();
        fragment1.setMlogo(R.drawable.backg);
        fragment1.setTextText("Salma!");
            ViewPagerFragment fragment2 = new ViewPagerFragment();
            fragment2.setTextText("Salmaa!");
            fragment2.setMlogo(R.drawable.biking);
        viewPagerAdaptor.addFragment(fragment1, "fragment1");
        viewPagerAdaptor.addFragment(fragment2, "fragment2");
        tableLayout = findViewById(R.id.tab_layout);
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdaptor);
    }
}