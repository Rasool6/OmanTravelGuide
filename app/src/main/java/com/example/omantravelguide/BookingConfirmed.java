package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BookingConfirmed extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmed);


        tabLayout=findViewById(R.id.tabID);
        tabItem1=findViewById(R.id.tab1);
        tabItem2=findViewById(R.id.tab2);


        //   def=item2.getTextColors();
        viewPager=findViewById(R.id.viewPagerId);

        final PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition()==0 || tab.getPosition()==1 ){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(BookingConfirmed.this,HomeActivity.class));
        finish();
    }

    public void back2(View view) {
        startActivity(new Intent(BookingConfirmed.this,HomeActivity.class));
        finish();
    }
}