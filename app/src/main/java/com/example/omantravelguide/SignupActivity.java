package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    Button btn_sent;
    EditText name,email,password;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...!");
        btn_sent=findViewById(R.id.btn_sent);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name=name.getText().toString().trim();
                String str_email=email.getText().toString().trim();
                String str_password=password.getText().toString().trim();
                if (TextUtils.isEmpty(str_name)){
                    name.setError("Empty Field..!");
                    return;
                }else
                if (TextUtils.isEmpty(str_email)){
                    email.setError("Empty Field..!");
                    return;
                }else
                    if (!isValidEmailId(email.getText().toString().trim())) {
                    email.setError("Enter valid email address");
                   // Snackbar.make(parent_view, "Enter valid email address", Snackbar.LENGTH_SHORT).show();
                    return;
                }else
                    if (TextUtils.isEmpty(str_password)){
                    password.setError("Empty Field..!");
                    return;
                }else {




                      authFunction(str_name,str_email,str_password);


                }
            }
        });
    }

    private void authFunction(String str_name, String str_email, String str_password) {

        mAuth.createUserWithEmailAndPassword(str_email,str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        final String TAG="";
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // email sent
                                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                                senddata(
                                                        currentUser.getUid(),
                                                        str_name,
                                                        str_email,
                                                        str_password

                                                );
                                                // after email is sent just logout the user and finish this activity
//                                                                       FirebaseAuth.getInstance().signOut();

                                            } else {
                                                // email not sent, so display message and restart the activity or do whatever you wish to do

                                                //restart this activity
                                                overridePendingTransition(0, 0);
                                                finish();
                                                overridePendingTransition(0, 0);
                                                startActivity(getIntent());

                                            }

                                        }
                                    });


//                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            //progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    private void senddata(String uid, String str_name, String str_email, String str_password) {
                   Appdata.UID=uid;
                    UserModel userModel=new UserModel(str_name,str_email,str_password);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(uid).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SignupActivity.this, "Profile  created successfully", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(SignupActivity.this, LoginAcitivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
    }

    private boolean isValidEmailId(String email) {
      /*  return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches(); */

        //    return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        String emailinput = email;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (emailinput.matches(emailPattern)) {
//            Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
            return true;
        } else {
//            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}