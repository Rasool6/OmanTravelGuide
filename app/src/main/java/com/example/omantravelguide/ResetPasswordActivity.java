package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPasswordActivity extends AppCompatActivity {


    EditText old_pass,newPass;
    Button btn_login;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...!");

        btn_login=findViewById(R.id.btn_login);
        old_pass=findViewById(R.id.oldPass);
        newPass=findViewById(R.id.newPass);

          String myEmail= getIntent().getStringExtra("key");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_old_pass=old_pass.getText().toString().trim();
                String str_newPass=newPass.getText().toString().trim();
                if (TextUtils.isEmpty(str_old_pass)){
                    old_pass.setError("Empty Field..!");
                    return;
                }else if (TextUtils.isEmpty(str_newPass)){
                    newPass.setError("Empty Field..!");
                    return;
                }else{
                    updatePassword(myEmail,str_old_pass,str_newPass);
                }
            }
        });
    }
    private void updatePassword(String str_myEmail,String str_old_pass1, String str_newPass1) {

        progressDialog.show();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.

        AuthCredential credential = EmailAuthProvider
                .getCredential(str_myEmail, str_old_pass1);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(str_newPass1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        senddata(user.getUid(),str_newPass1);
                                        Toast.makeText(ResetPasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                        Log.d("TAG", "Password updated");
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(ResetPasswordActivity.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.d("TAG", "Error auth failed");
                        }
                    }
                });



    }

    private void senddata(String uid, String newPass) {

        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("password").setValue(newPass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Log.e("TAG", "Data Not saved");
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Profile  created successfully", Toast.LENGTH_SHORT).show();
                    Log.e("ss", "Data saved successfully");
                    progressDialog.dismiss();
                }
            }
        });
    }

}