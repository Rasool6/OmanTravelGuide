package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TourBookingActivity extends AppCompatActivity {
    EditText edt_fullname,edt_email,edt_phoneNo,edt_noOfGuest;
    TextView edt_arriavlDate,edt_departuredate,pricetxt;

    ProgressBar progressBar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_booking);
        edt_fullname=findViewById(R.id.fullname1);
        edt_email=findViewById(R.id.email1);
        edt_phoneNo=findViewById(R.id.phoneNo1);
        edt_noOfGuest=findViewById(R.id.noOfGuests1);
        edt_arriavlDate=findViewById(R.id.arrivalDate1);
        edt_departuredate=findViewById(R.id.departureDate1);
        progressBar=findViewById(R.id.progressid);
        pricetxt=findViewById(R.id.priceID1);

        pricetxt.setText("OMR "+getIntent().getStringExtra("price"));
        edt_arriavlDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openDatePicker1();
            }
        });

        edt_departuredate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openDatePicker2();
            }
        });
    }

    public void bookinbtn1(View view) {
        String str_edt_fullname=edt_fullname.getText().toString().trim();
        String str_edt_email=edt_email.getText().toString().trim();
        String str_edt_phoneNo=edt_phoneNo.getText().toString().trim();
        String str_edt_noOfGuest=edt_noOfGuest.getText().toString().trim();
        String str_edt_arriavlDate=edt_arriavlDate.getText().toString().trim();
        String str_edt_departuredate=edt_departuredate.getText().toString().trim();

        if (TextUtils.isEmpty(str_edt_fullname)){
            edt_fullname.setError("Empty field..!");
        }else  if (TextUtils.isEmpty(str_edt_email)){
            edt_email.setError("Empty field..!");
        }else  if (TextUtils.isEmpty(str_edt_phoneNo)){
            edt_phoneNo.setError("Empty field..!");
        }else  if (TextUtils.isEmpty(str_edt_noOfGuest)){
            edt_noOfGuest.setError("Empty field..!");
        }else  if (TextUtils.isEmpty(str_edt_arriavlDate)){
            edt_arriavlDate.setError("Empty field..!");
        }else  if (TextUtils.isEmpty(str_edt_departuredate)){
            edt_departuredate.setError("Empty field..!");
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            BookingModel bookingModel=new BookingModel(
                    getIntent().getStringExtra("id"),
                    getIntent().getStringExtra("name"),
                    getIntent().getStringExtra("price"),
                    str_edt_fullname,
                    str_edt_email,
                    str_edt_phoneNo,
                   "",
                    str_edt_arriavlDate,
                    str_edt_departuredate,
                    str_edt_noOfGuest,
                    "0",
                    "Tourism"

            );

            FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(bookingModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(TourBookingActivity.this, "Booking done Successfully...!", Toast.LENGTH_SHORT).show();
                    edt_fullname.setText("");
                    edt_email.setText("");
                    edt_phoneNo.setText("");
                    edt_noOfGuest.setText("");
                    edt_arriavlDate.setText("");
                    edt_departuredate.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(TourBookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void openDatePicker1() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("TAG", "DATE SELECTED "+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        //PUT YOUR LOGING HERE
                        //UNCOMMENT THIS LINE TO CALL TIMEPICKER
                        //openTimePicker();
                        edt_arriavlDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void openDatePicker2() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("TAG", "DATE SELECTED "+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        //PUT YOUR LOGING HERE
                        //UNCOMMENT THIS LINE TO CALL TIMEPICKER
                        //openTimePicker();
                        edt_departuredate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void back7(View view) {
    }
}