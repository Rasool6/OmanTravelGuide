package com.example.omantravelguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AdminDashboard extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        tabLayout=findViewById(R.id.tabID);
        tabItem1=findViewById(R.id.tab1);
        tabItem2=findViewById(R.id.tab2);


        //   def=item2.getTextColors();
        viewPager=findViewById(R.id.viewPagerId);

        final PageAdapter2 pageAdapter=new PageAdapter2(getSupportFragmentManager(),tabLayout.getTabCount());
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
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(BookingConfirmed.this,HomeActivity.class));
//        finish();
//    }
//
//    public void back2(View view) {
//        startActivity(new Intent(BookingConfirmed.this,HomeActivity.class));
//        finish();
//    }
@Override
public void onBackPressed() {
    IsFinish("Want to close app?");
}

    public  void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}