package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    Spinner spinner_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        spinner_txt=findViewById(R.id.choose);

        spinner_txt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                  //  Toast.makeText(ChooseActivity.this, "Select User Role", Toast.LENGTH_SHORT).show();
                }else
                    if (position==1){
                        startActivity(new Intent(ChooseActivity.this,AdminLoginAcitivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(ChooseActivity.this,LoginAcitivity.class));
                        finish();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}