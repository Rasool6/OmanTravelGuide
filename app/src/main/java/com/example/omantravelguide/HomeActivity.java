package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
//    LinearLayout hotel,toursim,notification,user_profile;
    CardView hotel,toursim,notification,user_profile;
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        imageSlider=findViewById(R.id.slider);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.toure, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.bokng,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hotel_room,ScaleTypes.FIT));
          imageSlider.setImageList(slideModels,ScaleTypes.FIT);



        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PopularHotel.class));
            }
        });
        toursim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TourismActivity.class));
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,BookingConfirmed.class));
            }
        });
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SettingActivity.class));
            }
        });


    }
    public  void  init(){
        hotel=findViewById(R.id.cardview_1);
        toursim=findViewById(R.id.cardview_2);
        notification=findViewById(R.id.cardview_3);
        user_profile=findViewById(R.id.cardview_4);

    }
}