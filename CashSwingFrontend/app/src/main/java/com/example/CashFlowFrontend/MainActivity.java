package com.example.CashFlowFrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
//THIS IS THE MAIN ACTIVITY CLASS, HANDLING THE TABLAYOUT AND VIEWPAGER2
    TabLayout tabLayout;

    ViewPager2 viewPager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //inflating the main menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_navigate) {//for the add currency button

            Intent intent = new Intent(MainActivity.this, AddCurrency.class);
            startActivity(intent);
            return true;



        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //creating the tablayout and viewpager

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adp = new ViewPagerAdapter(this);
        this.viewPager.setAdapter(adp);

        new TabLayoutMediator(tabLayout, viewPager,//we have 2 pages
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Currency List");
                            break;
                        case 1:
                            tab.setText("Converter");
                            break;
                    }
                }
        ).attach();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }


}