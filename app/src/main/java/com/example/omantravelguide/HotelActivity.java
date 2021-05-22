package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

    ImageView image_view;
    TextView hotelName,hotelAddress,hotelRating,hotalPrice,hotelDetal;
    Button checkAvailabilitybtn;

    Bundle bundle;
    HotelModel hotelModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel1);

        image_view=findViewById(R.id.image_view);
        hotelName=findViewById(R.id.txt_location);
        hotelAddress=findViewById(R.id.txt_hotel);
        hotelRating=findViewById(R.id.ratingId);
        hotalPrice=findViewById(R.id.hotelPricing);
        hotelDetal=findViewById(R.id.txt_booking_paragraph1);
        checkAvailabilitybtn=findViewById(R.id.checkAvailability);

        bundle=getIntent().getExtras();
        hotelModel= (HotelModel) bundle.getSerializable("MYDATA");
      hotelName.setText(hotelModel.hotel_name);
     hotelAddress.setText(hotelModel.address);
       hotelRating.setText(hotelModel.expert_rating);
      hotalPrice.setText("OMR"+hotelModel.rate);
       hotelDetal.setText(hotelModel.detail);
        Glide.with(HotelActivity.this).load(hotelModel.imgUri).into(image_view);


        checkAvailabilitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity.this,Booking_ConfirmationActivity.class)
                .putExtra("id",hotelModel.id)
                .putExtra("name",hotelModel.hotel_name)
                .putExtra("price",hotelModel.rate)
                );
            }
        });

    }

    public void back5(View view) {
        startActivity(new Intent(HotelActivity.this,PopularHotel.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HotelActivity.this,PopularHotel.class));
        finish();
    }
}