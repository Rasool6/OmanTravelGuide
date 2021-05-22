package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingActivity extends AppCompatActivity {
        TextView nametxt,emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

                emailTxt=findViewById(R.id.textView11);
                  nametxt=findViewById(R.id.textView9);


                FetchData();

    }

    private void FetchData() {


        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    if (snapshot1.getKey().equals(Appdata.UID)){
                        nametxt.setText(snapshot1.child("name").getValue(String.class));
                        emailTxt.setText(snapshot1.child("email").getValue(String.class));
                   }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SettingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back1(View view) {
        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
        finish();
    }
}