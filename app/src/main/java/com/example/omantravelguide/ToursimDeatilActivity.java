package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ToursimDeatilActivity extends AppCompatActivity {
    ImageView image_view;
    TextView name,price,readMoreBtn,readMoreBtn2,readMoreBtn3;
    Bundle bundle;
    TourismModel tourismModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toursim_deatil);

        image_view=findViewById(R.id.imageView4);
        name=findViewById(R.id.textView3);
        price=findViewById(R.id.textView4);
        readMoreBtn=findViewById(R.id.textView5);


         bundle=getIntent().getExtras();
         tourismModel= (TourismModel) bundle.getSerializable("MYDATA");

        Glide.with(ToursimDeatilActivity.this).load(tourismModel.imgUrl).into(image_view);
        name.setText(tourismModel.name);
        price.setText(tourismModel.price);
        readMoreBtn.setText("Tour description\n"+tourismModel.tourDetail);
//        readMoreBtn2.setText("What's included\n"+tourismModel.include);
//        readMoreBtn3.setText("What's not included\n"+tourismModel.notInclude);
    }

    public void back4(View view) {
        startActivity(new Intent(ToursimDeatilActivity.this,TourismActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ToursimDeatilActivity.this,TourismActivity.class));
        finish();
    }

    public void nextbtn1(View view) {
        startActivity(new Intent(ToursimDeatilActivity.this,TourBookingActivity.class)
        .putExtra("id",tourismModel.toursim_id)
        .putExtra("name",name.getText().toString())
        .putExtra("price",price.getText().toString())
        );
        finish();

    }
}